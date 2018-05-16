package com.wm.remoting.model;

import io.netty.channel.ChannelHandlerContext;
import com.wm.common.exception.remoting.RemotingSendRequestException;
import com.wm.common.exception.remoting.RemotingTimeoutException;

/**
 * 
 * @author BazingaLyn
 * @description 处理channel关闭或者inactive的状态的时候的改变
 * @time 2016年8月15日
 * @modifytime
 */
public interface NettyChannelInactiveProcessor {
	
	
	void processChannelInactive(ChannelHandlerContext ctx) throws RemotingSendRequestException, RemotingTimeoutException, InterruptedException;
}
