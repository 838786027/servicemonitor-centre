package com.gosun.servicemonitor.event.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import com.gosun.servicemonitor.event.Event;

public class ServicerUpdateEvent extends Event{
	/**
	 * 请求发送时间
	 */
	private LocalDateTime sendTime;
	/**
	 * 请求到达时间
	 */
	private LocalDateTime arriveTime;
	/**
	 * 事件类型
	 */
	private Type type;
	/**
	 * 服务名称
	 */
	private String serviceName;
	
	public static enum Type{
		/**
		 * 注册
		 */
		register,
		/**
		 * 注销
		 */
		logout,
		/**
		 * 信息更新
		 */
		update,
		/**
		 * 节点更新
		 */
		nodeUpdate
	}

	public LocalDateTime getSendTime() {
		return sendTime;
	}

	public ServicerUpdateEvent setSendTime(LocalDateTime sendTime) {
		this.sendTime = sendTime;
		return this;
	}

	public LocalDateTime getArriveTime() {
		return arriveTime;
	}

	public ServicerUpdateEvent setArriveTime(LocalDateTime arriveTime) {
		this.arriveTime = arriveTime;
		return this;
	}

	public Type getType() {
		return type;
	}

	public ServicerUpdateEvent setType(Type type) {
		this.type = type;
		return this;
	}

	public String getServiceName() {
		return serviceName;
	}

	public ServicerUpdateEvent setServiceName(String serviceName) {
		this.serviceName = serviceName;
		return this;
	}
}
