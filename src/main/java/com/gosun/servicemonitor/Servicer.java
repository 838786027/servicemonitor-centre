package com.gosun.servicemonitor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 服务
 * 线程安全
 * @author caixiaopeng
 *
 */
public class Servicer {
	/**
	 * 服务名
	 * 唯一标示符
	 */
	private String name;
	
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
	 * 包含的节点
	 */
	private Set<Node> nodes=new CopyOnWriteArraySet<Node>();

	public String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public synchronized void setNote(String note) {
		this.note = note;
	}
	
	public Set<Node> getNodes() {
		return nodes;
	}

	public void addNode(Node node){
		this.nodes.add(node);
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

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Servicer other = (Servicer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
