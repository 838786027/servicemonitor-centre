package com.gosun.servicemonitor;

public class Main {
	public static void main(String[] args) {
		MonitorConfig config=new MonitorConfig();
		config.set(CommonParams.CENTRE_PORT, "9080")
			.set(CommonParams.PATROL_INTERVAL,"10000");
		
		MonitorContextBuilder builder=new MonitorContextBuilder();
		MonitorContext context=builder.config(config)
			.buildOrGet();
		context.start();
	}
}
