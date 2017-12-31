package com.gosun.servicemonitor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gosun.servicemonitor.event.impl.NodeUpdateEvent;
import com.gosun.servicemonitor.event.impl.ServicerUpdateEvent;
import com.gosun.servicemonitor.event.impl.NodeUpdateEvent.Type;

/**
 * 服务与节点集成控制中心 单例，线程安全
 * 
 * @author caixiaopeng
 *
 */
public class ServicerAndNodeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServicerAndNodeController.class);
	private static ServicerAndNodeController instance;
	private MonitorContext context;

	private ServicerAndNodeController() {
	}

	/**
	 * 节点集合 键：node.id
	 * 线程安全
	 */
	private Map<String, Node> nodes = new ConcurrentHashMap<String, Node>();

	//private Set<Servicer> servicers = new CopyOnWriteArraySet<Servicer>();
	
	/**
	 * 服务集合 键：servicer.name
	 * 线程安全
	 */
	private Map<String, Servicer> servicers=new ConcurrentHashMap<String, Servicer>();
	
	/**
	 * 整体锁
	 */
	private Lock lock = new ReentrantLock();

	/**
	 * 添加节点 ps.自动添加服务
	 * 
	 * @param node
	 *            node.servicer不能为空
	 * @return
	 */
	public ServicerAndNodeController addNode(Node node) {
		Servicer servicer = node.getServicer();
		if (servicer == null) {
			// 添加失败
			return this;
		}
		
		nodes.put(node.getId(), node);

		// 添加服务
		boolean isServicerAlreadyExist=false;
		if (!servicers.containsKey(servicer.getName())) {
			servicers.put(servicer.getName(),servicer);
		} else {
			isServicerAlreadyExist=true;
			// 将已有服务与节点关联
			servicers.get(servicer.getName()).addNode(node);
		}
		
		// 发送注册消息
		ServicerUpdateEvent servicerUpdateEvent = new ServicerUpdateEvent();
		NodeUpdateEvent nodeUpdateEvent = new NodeUpdateEvent();
		if(!isServicerAlreadyExist){
			servicerUpdateEvent.setType(com.gosun.servicemonitor.event.impl.ServicerUpdateEvent.Type.register);
		}else{
			servicerUpdateEvent.setType(com.gosun.servicemonitor.event.impl.ServicerUpdateEvent.Type.nodeUpdate);
		}
		servicerUpdateEvent.setArriveTime(LocalDateTime.now())
				.setServiceName("" + servicer.getName())
				.setParentEvent(nodeUpdateEvent);
		nodeUpdateEvent.setType(Type.register)
				.setArriveTime(LocalDateTime.now())
				.setNodeId("" + node.getId())
				.setServicerName("" + servicer.getName())
				.setNodeIp(""+node.getIp())
				.addSubEvents(servicerUpdateEvent);
		context.getListenerBus().post(nodeUpdateEvent);
		return this;
	}

	public Node getNode(String nodeId){
		return nodes.get(nodeId);
	}
	
	public List<Node> getNodes() {
		return new LinkedList<Node>(nodes.values());
	}
	
	public void removeNode(String nodeId){
		nodes.remove(nodeId);
	}

	public List<Servicer> getServicers() {
		return new LinkedList<Servicer>(servicers.values());
	}
	
	public Servicer getServicer(String servicerName){
		return servicers.get(servicerName);
	}
	
	public void removeServicer(String servicerName){
		servicers.remove(servicerName);
	}
	
	public MonitorContext getContext() {
		return context;
	}

	public void setContext(MonitorContext context) {
		this.context = context;
	}

	public synchronized static ServicerAndNodeController getInstance() {
		if (instance != null) {
			return instance;
		}
		instance = new ServicerAndNodeController();
		return instance;
	}
}
