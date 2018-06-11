package com.wm.base.registry;

import io.netty.channel.Channel;
import com.wm.common.exception.remoting.RemotingSendRequestException;
import com.wm.common.exception.remoting.RemotingTimeoutException;
import com.wm.remoting.model.RemotingTransporter;

/**
 * 
 * @author BazingaLyn
 * @description 注册中心处理provider的服务接口
 * @time 2016年8月15日
 * @modifytime
 */
public interface RegistryProviderServer {
	
	
	/**
	 * 处理provider发送过来的注册信息
	 * @param remotingTransporter 里面的CommonCustomBody 是#PublishServiceCustomBody
	 * @param channel
	 * @return
	 * @throws InterruptedException 
	 * @throws RemotingTimeoutException 
	 * @throws RemotingSendRequestException 
	 */
	RemotingTransporter handlerRegister(RemotingTransporter remotingTransporter, Channel channel) throws RemotingSendRequestException, RemotingTimeoutException, InterruptedException;
}
