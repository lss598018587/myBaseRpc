package com.wm.example.demo.service;

import com.wm.client.annotation.RPCService;

/**
 * 
 * @author BazingaLyn
 * @description Demo
 * @time 2016年8月19日
 * @modifytime
 */
public class HelloSerivceImpl implements HelloSerivce {

	@Override
	@RPCService(responsibilityName="xiaoy",
				serviceName="LAOPOPO.TEST.SAYHELLO",
				isVIPService = false,
				isSupportDegradeService = true,
				degradeServicePath="com.wm.example.demo.service.HelloServiceMock",
				degradeServiceDesc="默认返回hello")
	public String sayHello(String str) {
		
		//真实逻辑可能涉及到查库
		return "hello "+ str;
		
	}

}
