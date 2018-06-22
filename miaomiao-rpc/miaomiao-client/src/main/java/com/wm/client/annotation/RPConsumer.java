package com.wm.client.annotation;

import java.lang.annotation.*;

/**
 * 
 * @author BazingaLyn
 * @description
 * @time
 * @modifytime
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Documented
public @interface RPConsumer {
	
	public String serviceName() default "";//服务名
	
}
