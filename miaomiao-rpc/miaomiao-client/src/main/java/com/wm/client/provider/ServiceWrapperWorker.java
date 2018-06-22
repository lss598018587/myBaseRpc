package com.wm.client.provider;

import com.wm.client.provider.interceptor.ProviderProxyHandler;
import com.wm.client.provider.model.ServiceWrapper;

import java.util.List;


/**
 * 
 * @author BazingaLyn
 * @description
 * @time
 * @modifytime
 */
public interface ServiceWrapperWorker {
	
	ServiceWrapperWorker provider(Object serviceProvider);
	
	ServiceWrapperWorker provider(ProviderProxyHandler proxyHandler, Object serviceProvider);
	
	List<ServiceWrapper> create();

}
