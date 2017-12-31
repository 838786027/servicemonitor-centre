package com.gosun.servicemonitor.event;

import java.util.LinkedList;
import java.util.List;

/**
 * 事件基类
 * @author caixiaopeng
 *
 */
public abstract class Event {
	/**
	 * 子事件
	 */
	protected List<Event> subEvents=new LinkedList<Event>();
	
	/**
	 * 父事件
	 */
	protected Event parentEvent;
	
	/**
	 * 添加子事件
	 * @param event 为null时添加失败
	 */
	public void addSubEvents(Event event){
		if(event!=null){
			subEvents.add(event);
		}
	}

	public Event getParentEvent() {
		return parentEvent;
	}

	public void setParentEvent(Event parentEvent) {
		this.parentEvent = parentEvent;
	}
}
