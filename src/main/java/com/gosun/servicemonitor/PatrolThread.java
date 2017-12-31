package com.gosun.servicemonitor;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gosun.servicemonitor.event.impl.NodeUpdateEvent;
import com.gosun.servicemonitor.event.impl.NodeUpdateEvent.Type;
import com.gosun.servicemonitor.event.impl.ServicerUpdateEvent;

/**
 * 巡逻线程
 * 单例，线程安全
 * @author caixiaopeng
 *
 */
public class PatrolThread extends Thread{
	private static final Logger LOGGER=LoggerFactory.getLogger(PatrolThread.class);
	private static PatrolThread instance;
	private MonitorContext context;
	/**
	 * 巡逻间隔
	 * 单位ms
	 * 默认值1000
	 */
	private long interval=1000;
	/**
	 * 节点超时时间
	 * 单位ms
	 * 默认值30*000
	 */
	private long sessionTimeout=30*1000;

	private PatrolThread(){
		setName("监控中心——巡逻线程");
	}
	
	public void run(){
		while(true){
			LOGGER.debug("巡逻线程开始工作");
			// note：待做 线程安全
			long nowMilli=Instant.now().toEpochMilli(); // 当前时间戳，用于做过期判断
			ServicerAndNodeController snController=context.getSnController();
			List<Node> nodes = snController.getNodes();
			for(Node node:nodes) {
				long lastTimeMilli = node.getUpdateTime().toEpochMilli();
				if ((nowMilli - lastTimeMilli) > sessionTimeout) {
					// 节点过期，注销节点
					LOGGER.info("发现过期节点，准备注销");
					LOGGER.info("\t id=" + node.getId()+"["+node.getServicer().getName()+":"+node.getIp()+"]");

					NodeUpdateEvent nodeUpdateEvent = new NodeUpdateEvent();
					ServicerUpdateEvent servicerUpdateEvent = null;
					Servicer servicer = node.getServicer();
					servicer.getNodes().remove(node);
					if (servicer.getNodes().isEmpty()) {
						// 服务下的节点为空，注销服务
						LOGGER.info("发现无节点的空服务，准备注销");
						LOGGER.info("\t name=" + servicer.getName());
						snController.removeServicer(servicer.getName());
						servicerUpdateEvent = new ServicerUpdateEvent();
						servicerUpdateEvent.setArriveTime(LocalDateTime.now())
								.setSendTime(LocalDateTime.now())
								.setType(com.gosun.servicemonitor.event.impl.ServicerUpdateEvent.Type.logout)
								.setServiceName(servicer.getName()).setParentEvent(nodeUpdateEvent);
					}
					snController.removeNode(node.getId());
					// 发送节点过期消息
					nodeUpdateEvent.setType(Type.logout)
							.setArriveTime(LocalDateTime.now())
							.setSendTime(LocalDateTime.now())
							.setServicerName(servicer.getName())
							.setNodeId(node.getId())
							.setNodeIp(node.getIp())
							.addSubEvents(servicerUpdateEvent);

					context.getListenerBus().post(nodeUpdateEvent);
				}
			}
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				LOGGER.error("巡逻线程睡眠失败，导致巡逻线程死亡",e);
				return ;
			}
		}
	}
	
	public synchronized static PatrolThread getInstance(){
		if(instance!=null){
			return instance;
		}
		instance=new PatrolThread();
		return instance;
	}
	
	public MonitorContext getContext() {
		return context;
	}
	
	public void setContext(MonitorContext context) {
		this.context = context;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public long getSessionTimeout() {
		return sessionTimeout;
	}

	public void setSessionTimeout(long sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}
}
