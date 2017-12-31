package com.gosun.servicemonitor.rpc.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.avro.AvroRemoteException;
import org.apache.avro.ipc.NettyTransceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gosun.servicemonitor.MonitorContext;
import com.gosun.servicemonitor.event.impl.HeartbeatEvent;
import com.gosun.servicemonitor.rpc.Heartbeat;
import com.gosun.servicemonitor.rpc.Info;
import com.gosun.servicemonitor.rpc.MonitorCentreInterface;
import com.gosun.servicemonitor.rpc.MonitorClientInterface;
import com.gosun.servicemonitor.rpc.Node;
import com.gosun.servicemonitor.rpc.Response;
import com.gosun.servicemonitor.rpc.RpcEnv;
import com.gosun.servicemonitor.rpc.Servicer;

public class MonitorCentreImpl implements MonitorCentreInterface {
	private static final Logger LOGGER = LoggerFactory.getLogger(MonitorCentreImpl.class);
	private MonitorContext context;

	public MonitorCentreImpl(MonitorContext context) {
		this.context = context;
	}

	@Override
	public Response sendHeartbeat(Heartbeat heartbeat, Info info) throws AvroRemoteException {
		LOGGER.debug("接收到心跳请求");
		LOGGER.debug("\t id=" + heartbeat.nodeId);
		LOGGER.debug("\t sendTime=" + info.sendTime);
		LOGGER.debug("\t extraMsg=" + heartbeat.extraMsg);
		Response response = new Response();
		response.setStatus(0);
		response.setMsg("成功接收心跳请求");
		response.setQTime(-1L);

		// 激活节点
		com.gosun.servicemonitor.Node node = context.getSnController().getNode("" + heartbeat.nodeId);
		if (node != null) {
			node.setUpdateTime(Instant.now());
		} else {
			// 心跳异常
			response.setStatus(2);
			response.setMsg("节点不存在");
			return response;
		}

		// 发送心跳消息
		HeartbeatEvent event = new HeartbeatEvent();
		event.setSendTime(LocalDateTime.parse(info.sendTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		event.setArriveTime(LocalDateTime.now());
		event.setNodeId("" + heartbeat.nodeId);
		event.setExtraMsg("" + heartbeat.extraMsg);
		context.getListenerBus().post(event);

		return response;
	}

	@Override
	public Response registerNode(RpcEnv rpcEnv, Node node, Servicer servicer, Info info) throws AvroRemoteException {
		LOGGER.info("接收到节点注册请求");
		LOGGER.info("\t id=" + node.id);
		LOGGER.info("\t ip=" + node.ip);
		LOGGER.info("\t service=" + servicer.getName());
		LOGGER.info("\t sendTime=" + info.sendTime);
		Response response = new Response();
		response.setStatus(0);
		response.setMsg("成功接收注册请求");
		response.setQTime(-1L);

		// 数据准备
		com.gosun.servicemonitor.Node nodeTemp = new com.gosun.servicemonitor.Node();
		nodeTemp.setId("" + node.getId());
		nodeTemp.setIp("" + node.getIp());
		nodeTemp.setNote("" + node.getNote());
		nodeTemp.setRole("" + node.getRole());
		nodeTemp.setCreateTime(Instant.now());
		nodeTemp.setUpdateTime(Instant.now());
		com.gosun.servicemonitor.Servicer serviceTemp = new com.gosun.servicemonitor.Servicer();
		serviceTemp.setName("" + servicer.getName());
		serviceTemp.setNote("" + servicer.getNote());
		serviceTemp.addNode(nodeTemp);
		serviceTemp.setCreateTime(Instant.now());
		serviceTemp.setUpdateTime(Instant.now());
		nodeTemp.setServicer(serviceTemp);
		nodeTemp.setRpcEnv(rpcEnv);

		// 使用新线程连接节点rpc（避免出现await*() in I/O thread causes a dead lock）
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String nodeRpcIp = "" + rpcEnv.getIp();
					int nodeRpcPort = rpcEnv.getPort();
					NettyTransceiver rpcClient = new NettyTransceiver(new InetSocketAddress(nodeRpcIp, nodeRpcPort));
					MonitorClientInterface rpcProxy = (MonitorClientInterface) SpecificRequestor
							.getClient(MonitorClientInterface.class, rpcClient);
					nodeTemp.setRpcClient(rpcClient);
					nodeTemp.setRpcProxy(rpcProxy);
				} catch (IOException e) {
					LOGGER.error("连接节点rpc时发生异常，可能导致某些功能无法正常使用", e);
				}
			}
		}).start();

		context.getSnController().addNode(nodeTemp);

		return response;
	}
}
