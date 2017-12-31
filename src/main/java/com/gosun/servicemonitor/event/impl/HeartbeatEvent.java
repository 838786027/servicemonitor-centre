package com.gosun.servicemonitor.event.impl;

import java.time.LocalDateTime;
import java.util.Date;

import com.gosun.servicemonitor.event.Event;

public class HeartbeatEvent extends Event{
	/**
	 * 发送时间
	 */
	private LocalDateTime sendTime;
	
	/**
	 * 到达时间
	 */
	private LocalDateTime arriveTime;
	
	/**
	 * 节点id
	 */
	private String nodeId;
	
	/**
	 * 额外信息
	 */
	private String extraMsg;

	public LocalDateTime getSendTime() {
		return sendTime;
	}

	public HeartbeatEvent setSendTime(LocalDateTime sendTime) {
		this.sendTime = sendTime;
		return this;
	}

	public LocalDateTime getArriveTime() {
		return arriveTime;
	}

	public HeartbeatEvent setArriveTime(LocalDateTime arriveTime) {
		this.arriveTime = arriveTime;
		return this;
	}

	public String getNodeId() {
		return nodeId;
	}

	public HeartbeatEvent setNodeId(String nodeId) {
		this.nodeId = nodeId;
		return this;
	}

	public String getExtraMsg() {
		return extraMsg;
	}

	public HeartbeatEvent setExtraMsg(String extraMsg) {
		this.extraMsg = extraMsg;
		return this;
	}
}
