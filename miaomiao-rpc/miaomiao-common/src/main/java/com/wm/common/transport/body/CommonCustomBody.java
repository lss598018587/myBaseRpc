package com.wm.common.transport.body;


import com.wm.common.exception.remoting.RemotingCommmonCustomException;

/**
 * 
 * @author BazingaLyn
 * @description 网络传输对象的主体对象
 * @time 2016年8月10日
 * @modifytime
 */
public interface CommonCustomBody {
	
    void checkFields() throws RemotingCommmonCustomException;
}
