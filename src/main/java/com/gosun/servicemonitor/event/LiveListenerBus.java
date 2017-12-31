package com.gosun.servicemonitor.event;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 事件监听总线
 * 
 * 以事件作为驱动
 * 
 * @author caixiaopeng
 *
 */
public class LiveListenerBus implements Closeable {
	/**
	 * 订阅者
	 */
	private Map<Class<? extends Event>, List<Listener>> subscribers = new HashMap<Class<? extends Event>, List<Listener>>();

	/**
	 * 事件调度线程池
	 */
	private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

	/**
	 * 注册监听器 线程安全
	 * 
	 * @param event
	 * @param listener
	 * @return
	 */
	public synchronized LiveListenerBus register(Class<? extends Event> event, Listener listener) {
		List<Listener> listeners = subscribers.get(event);
		if (listeners == null) {
			listeners = new LinkedList<Listener>();
			subscribers.put(event, listeners);
		}

		listeners.add(listener);
		return this;
	}

	/**
	 * 发送事件
	 * 
	 * @param event
	 */
	public void post(Event event) {
		Class<? extends Event> eventClass = event.getClass();
		List<Listener> listeners = subscribers.get(eventClass);
		if (listeners != null) {
			for (Listener listener : listeners) {
				executor.execute(new Runnable() {
					@Override
					public void run() {
						listener.handle(event);
					}
				});
			}
		}
		// 子事件递归
		for(Event e:event.subEvents){
			post(e);
		}
	}

	@Override
	public void close() throws IOException {
		// 关闭线程池
		executor.shutdown();
	}
}
