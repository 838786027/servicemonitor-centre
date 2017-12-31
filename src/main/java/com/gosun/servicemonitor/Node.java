package com.gosun.servicemonitor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.avro.ipc.NettyTransceiver;

import com.gosun.servicemonitor.rpc.MonitorCentreInterface;
import com.gosun.servicemonitor.rpc.MonitorClientInterface;
import com.gosun.servicemonitor.rpc.RpcEnv;

/**
 * 服务节点
 * 线程安全
 * @author caixiaopeng
 *
 */
public class Node {
	/**
	 * 所属服务
	 */
	private Servicer servicer;
	
	/**
	 * 标识符
	 */
	private String id;
	
	/**
	 * ip地址
	 */
	private String ip;
	
	/**
	 * 角色代号
	 */
	private String role;
	
	/**
	 * 描述
	 */
	private String note;
	
	/**
	 * 更新时间
	 */
	private Instant updateTime;
	
	/**
	 * 创建时间
	 */
	private Instant createTime;
	
	/**
	 * rpc信息
	 */
	private RpcEnv rpcEnv;
	
	/**
	 * 节点rpc客户端
	 */
	private NettyTransceiver rpcClient;

	/**
	 * 节点rpc代理
	 */
	private MonitorClientInterface rpcProxy;

	public Servicer getServicer() {
		return servicer;
	}

	public synchronized void setServicer(Servicer servicer) {
		this.servicer = servicer;
	}

	public String getId() {
		return id;
	}

	public synchronized void setId(String id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRole() {
		return role;
	}

	public synchronized void setRole(String role) {
		this.role = role;
	}

	public String getNote() {
		return note;
	}

	public synchronized void setNote(String note) {
		this.note = note;
	}

	public Instant getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Instant updateTime) {
		this.updateTime = updateTime;
	}

	public Instant getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Instant createTime) {
		this.createTime = createTime;
	}

	public RpcEnv getRpcEnv() {
		return rpcEnv;
	}

	public void setRpcEnv(RpcEnv rpcEnv) {
		this.rpcEnv = rpcEnv;
	}

	public NettyTransceiver getRpcClient() {
		return rpcClient;
	}

	public void setRpcClient(NettyTransceiver rpcClient) {
		this.rpcClient = rpcClient;
	}

	public MonitorClientInterface getRpcProxy() {
		return rpcProxy;
	}

	public void setRpcProxy(MonitorClientInterface rpcProxy) {
		this.rpcProxy = rpcProxy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
