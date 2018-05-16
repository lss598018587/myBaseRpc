package com.wm.example.netty;

import io.netty.channel.ChannelHandlerContext;
import com.wm.common.protocal.LaopopoProtocol;
import com.wm.remoting.model.NettyRequestProcessor;
import com.wm.remoting.model.RemotingTransporter;
import com.wm.remoting.netty.NettyRemotingServer;
import com.wm.remoting.netty.NettyServerConfig;

import java.util.concurrent.Executors;

import static com.wm.common.serialization.SerializerHolder.serializerImpl;

public class NettyServerTest {
	
	public static final byte TEST = -1;
	
	public static void main(String[] args) {
		
		NettyServerConfig config = new NettyServerConfig();
		config.setListenPort(18001);
		NettyRemotingServer server = new NettyRemotingServer(config);
		server.registerProecessor(TEST, new NettyRequestProcessor() {
			
			@Override
			public RemotingTransporter processRequest(ChannelHandlerContext ctx, RemotingTransporter transporter) throws Exception {
				transporter.setCustomHeader(serializerImpl().readObject(transporter.bytes(), TestCommonCustomBody.class));
				System.out.println(transporter);
				transporter.setTransporterType(LaopopoProtocol.RESPONSE_REMOTING);
				return transporter;
			}
		}, Executors.newCachedThreadPool());
		server.start();
	}

}
