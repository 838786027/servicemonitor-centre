package com.gosun.servicemonitor.event.impl;

import java.time.LocalDateTime;
import java.util.Date;

import com.gosun.servicemonitor.event.Event;

public class NodeUpdateEvent extends Event{
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
	 * 节点id
	 */
	private String nodeId;
	/**
	 * 节点ip
	 */
	private String nodeIp;
	/**
	 * 服务名
	 */
	private String servicerName;
	
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
		update
	}

	public LocalDateTime getSendTime() {
		return sendTime;
	}

	public NodeUpdateEvent setSendTime(LocalDateTime sendTime) {
		this.sendTime = sendTime;
		return this;
	}

	public LocalDateTime getArriveTime() {
		return arriveTime;
	}

	public NodeUpdateEvent setArriveTime(LocalDateTime arriveTime) {
		this.arriveTime = arriveTime;
		return this;
	}

	public Type getType() {
		return type;
	}

	public NodeUpdateEvent setType(Type type) {
		this.type = type;
		return this;
	}

	public String getNodeId() {
		return nodeId;
	}

	public NodeUpdateEvent setNodeId(String nodeId) {
		this.nodeId = nodeId;
		return this;
	}

	public String getServicerName() {
		return servicerName;
	}

	public NodeUpdateEvent setServicerName(String servicerName) {
		this.servicerName = servicerName;
		return this;
	}

	public String getNodeIp() {
		return nodeIp;
	}

	public NodeUpdateEvent setNodeIp(String nodeIp) {
		this.nodeIp = nodeIp;
		return this;
	}
}
