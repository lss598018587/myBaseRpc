package com.wm.example.netty;

import com.wm.common.exception.remoting.RemotingException;
import com.wm.example.netty.TestCommonCustomBody.ComplexTestObj;
import com.wm.remoting.model.RemotingTransporter;
import com.wm.remoting.netty.NettyClientConfig;
import com.wm.remoting.netty.NettyRemotingClient;

public class NettyClientTest {
	
	public static final byte TEST = -1;
	
	public static void main(String[] args) throws InterruptedException, RemotingException {
		NettyClientConfig nettyClientConfig = new NettyClientConfig();
		NettyRemotingClient client = new NettyRemotingClient(nettyClientConfig);
		client.start();
		
		ComplexTestObj complexTestObj = new ComplexTestObj("attr1", 2);
		TestCommonCustomBody commonCustomHeader = new TestCommonCustomBody(1, "test",complexTestObj);
		
		RemotingTransporter remotingTransporter = RemotingTransporter.createRequestTransporter(TEST, commonCustomHeader);
		RemotingTransporter request = client.invokeSync("127.0.0.1:18001", remotingTransporter, 30000);
		System.out.println(request);
	}
	
}
