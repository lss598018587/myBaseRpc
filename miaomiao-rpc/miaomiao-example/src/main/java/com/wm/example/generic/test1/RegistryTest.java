package com.wm.example.generic.test1;

import io.netty.util.internal.ConcurrentSet;
import com.wm.base.registry.DefaultRegistryServer;
import com.wm.base.registry.RegistryServerConfig;
import com.wm.common.rpc.RegisterMeta;
import com.wm.common.rpc.RegisterMeta.Address;
import com.wm.common.rpc.ServiceReviewState;
import com.wm.remoting.netty.NettyServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentMap;

public class RegistryTest {
	
	private static final Logger logger = LoggerFactory.getLogger(RegistryTest.class);
	
	private static DefaultRegistryServer defaultRegistryServer;
	
	public static void main(String[] args) {
		
		Thread t = new Thread(new RegistryTest1Scanner(), "timeout.scanner");
        t.setDaemon(true);
        t.start();
        
		NettyServerConfig config = new NettyServerConfig();
		RegistryServerConfig registryServerConfig = new RegistryServerConfig();
		registryServerConfig.setDefaultReviewState(ServiceReviewState.PASS_REVIEW);
		//注册中心的端口号
		config.setListenPort(18010);
		defaultRegistryServer = new DefaultRegistryServer(config,registryServerConfig);
		defaultRegistryServer.start();
		
	}
	
	
	private static class RegistryTest1Scanner implements Runnable {

        @Override
        public void run() {
        	
            for (;;) {
                try {
                	logger.info("统计中");
                	Thread.sleep(10000);
                	ConcurrentMap<String, ConcurrentMap<Address, RegisterMeta>> concurrentMap = defaultRegistryServer.getProviderManager().getGlobalRegisterInfoMap();
                    if(null != concurrentMap){
                    	for(String serviceName:concurrentMap.keySet()){
                    		ConcurrentMap<Address, RegisterMeta> map = concurrentMap.get(serviceName);
                    		if(map != null){
                    			for(Address address : map.keySet()){
                    				logger.info("serviceName [{}] address [{}] and detail [{}]",serviceName,address,map.get(address).toString());
                    			}
                    		}
                    	}
                    }
                    
                    ConcurrentMap<Address, ConcurrentSet<String>> serviceMap = defaultRegistryServer.getProviderManager().getGlobalServiceMetaMap();
                    if(null != serviceMap){
                    	for(Address address : serviceMap.keySet()){
                    		if(null != serviceMap.get(address)){
                    			for(String str : serviceMap.get(address)){
                    				logger.info("address [{}] provider serivcename [{}]",address,str);
                    			}
                    		}
                    	}
                    }
                } catch (Throwable t) {
                    logger.error("An exception has been caught while scanning the timeout acknowledges {}.", t);
                }
            }
        }
    }

}
