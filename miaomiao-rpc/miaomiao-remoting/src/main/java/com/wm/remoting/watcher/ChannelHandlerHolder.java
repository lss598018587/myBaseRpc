package com.wm.remoting.watcher;

import io.netty.channel.ChannelHandler;

/**
 * 
 * @author BazingaLyn
 *
 * @time
 */
public interface ChannelHandlerHolder {

	ChannelHandler[] handlers();
	
}
