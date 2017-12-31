package com.gosun.servicemonitor;

/**
 * 基础配置参数
 * @author caixiaopeng
 *
 */
public class CommonParams {
	/**
	 * 监控中心rpc使用的端口
	 */
	public static final String CENTRE_PORT="servicemonitor.centre.port";
	/**
	 * 节点超时时间
	 */
	public static final String SESSION_TIMEOUT="servicemonitor.session.timeout.ms";
	/**
	 * 巡逻检测周期
	 */
	public static final String PATROL_INTERVAL="servicemonitor.patrol.interval.ms";
}
