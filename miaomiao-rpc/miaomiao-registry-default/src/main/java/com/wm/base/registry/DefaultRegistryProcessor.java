package com.wm.base.registry;

import io.netty.channel.ChannelHandlerContext;
import com.wm.remoting.ConnectionUtils;
import com.wm.remoting.model.NettyRequestProcessor;
import com.wm.remoting.model.RemotingTransporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.wm.common.protocal.LaopopoProtocol.*;

/**
 * 
 * @author BazingaLyn
 * @description 注册中心的处理转换器
 * @time 2016年8月13日
 * @modifytime 2016年8月31日
 */
public class DefaultRegistryProcessor implements NettyRequestProcessor {

	private static final Logger logger = LoggerFactory.getLogger(DefaultRegistryProcessor.class);

	private DefaultRegistryServer defaultRegistryServer;

	public DefaultRegistryProcessor(DefaultRegistryServer defaultRegistryServer) {
		this.defaultRegistryServer = defaultRegistryServer;
	}

	@Override
	public RemotingTransporter processRequest(ChannelHandlerContext ctx, RemotingTransporter request) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("receive request, {} {} {}",//
					request.getCode(), //
					ConnectionUtils.parseChannelRemoteAddr(ctx.channel()), //
					request);
		}
		System.out.println("注册服务接口了");

		switch (request.getCode()) {

			case PUBLISH_SERVICE: // 处理服务提供者provider推送的服务信息
				return this.defaultRegistryServer.getProviderManager().handlerRegister(request, ctx.channel()); // 要保持幂等性，同一个实例重复发布同一个服务的时候对于注册中心来说是无影响的
			case PUBLISH_CANCEL_SERVICE: // 处理服务提供者provider推送的服务取消的信息
				return this.defaultRegistryServer.getProviderManager().handlerRegisterCancel(request, ctx.channel());
			case SUBSCRIBE_SERVICE: // 处理服务消费者consumer订阅服务的请求
				return this.defaultRegistryServer.getProviderManager().handleSubscribe(request, ctx.channel());
			case MANAGER_SERVICE: // 处理管理者发送过来的服务管理服务
				return this.defaultRegistryServer.getProviderManager().handleManager(request, ctx.channel());
		}

		return null;
	}

}
