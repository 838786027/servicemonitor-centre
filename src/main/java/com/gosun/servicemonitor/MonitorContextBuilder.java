package com.gosun.servicemonitor;

import com.gosun.servicemonitor.event.LiveListenerBus;
import com.gosun.servicemonitor.event.impl.HeartbeatEvent;

/**
 * 监听中心上下文构建器
 * @author caixiaopeng
 *
 */
public class MonitorContextBuilder {
	private MonitorConfig config;
	private MonitorContext context;
	
	public MonitorContextBuilder config(MonitorConfig config){
		this.config=config;
		return this;
	}
	
	/**
	 * 不允许重复构建
	 * 
	 */
	public MonitorContext buildOrGet(){
		if(context!=null){
			return context;
		}
		
		// 初始化上下文
		context=MonitorContext.getInstance();
		context.getRpcEnv().setPort(Integer.valueOf(""+config.get(CommonParams.CENTRE_PORT, "9080")));
		
		// 初始化服务与节点集成控制中心
		context.getSnController().setContext(context);
		
		// 初始化巡逻线程
		PatrolThread patrolThread=context.getPatrolThread();
		patrolThread.setContext(context);
		patrolThread.setInterval(Long.valueOf(""+config.get(CommonParams.PATROL_INTERVAL,"1000")));
		patrolThread.setSessionTimeout(Long.valueOf(""+config.get(CommonParams.SESSION_TIMEOUT,"30000")));
	
		return context;
	}
}
