package com.gosun.servicemonitor;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.avro.ipc.NettyServer;
import org.apache.avro.ipc.Server;
import org.apache.avro.ipc.specific.SpecificResponder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gosun.servicemonitor.event.LiveListenerBus;
import com.gosun.servicemonitor.rpc.MonitorCentreInterface;
import com.gosun.servicemonitor.rpc.RpcEnv;
import com.gosun.servicemonitor.rpc.impl.MonitorCentreImpl;
import com.gosun.util.InternetUtils;

/**
 * 监控中心上下文
 * 单例，线程安全
 * @author caixiaopeng
 *
 */
public class MonitorContext {
	private static final Logger LOGGER=LoggerFactory.getLogger(MonitorContext.class);
	private static MonitorContext instance;
	
	private RpcEnv rpcEnv=new RpcEnv();
	
	/**
	 * 事件总线
	 */
	private LiveListenerBus listenerBus=new LiveListenerBus();
	
	/**
	 * 服务与节点集成控制中心
	 */
	private ServicerAndNodeController snController=ServicerAndNodeController.getInstance();
	
	/**
	 * 巡逻线程
	 */
	private PatrolThread patrolThread=PatrolThread.getInstance();
	
	private MonitorContext(){
		rpcEnv.setIp(InternetUtils.getLocalHostLANAddress().getHostAddress());
	}
	
	public void start(){
		LOGGER.info("监听中心启动");
		Server server = new NettyServer(new SpecificResponder(MonitorCentreInterface.class, new MonitorCentreImpl(this)),
				new InetSocketAddress(rpcEnv.getPort()));
		server.start();
		patrolThread.start();
	}
	
	public synchronized static MonitorContext getInstance(){
		if(instance!=null){
			return instance;
		}
		instance=new MonitorContext();
		return instance;
	}
	
	public LiveListenerBus getListenerBus() {
		return listenerBus;
	}

	public ServicerAndNodeController getSnController() {
		return snController;
	}

	public PatrolThread getPatrolThread() {
		return patrolThread;
	}

	public RpcEnv getRpcEnv() {
		return rpcEnv;
	}
	
//	public static void main(String[] args) throws InterruptedException {
//		Server server = new NettyServer(new SpecificResponder(HeartbeatRequest.class, new HeartbeatRequsetImpl()),
//				new InetSocketAddress(9080));
//		server.start();
//		Thread.sleep(60*60*1000);
//	}
}
