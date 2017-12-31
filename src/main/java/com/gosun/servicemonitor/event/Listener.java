package com.gosun.servicemonitor.event;

public interface Listener<T extends Event> {
	void handle(T event);
}
