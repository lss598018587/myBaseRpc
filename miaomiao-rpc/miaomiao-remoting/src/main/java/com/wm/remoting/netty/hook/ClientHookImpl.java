package com.wm.remoting.netty.hook;

import com.wm.remoting.RPCHook;
import com.wm.remoting.model.RemotingTransporter;

/**
 * @Auther: miaomiao
 * @Date: 18/6/7 14:26
 * @Description:
 */
public class ClientHookImpl implements RPCHook {
    @Override
    public void doBeforeRequest(String remoteAddr, RemotingTransporter request) {
    }

    @Override
    public void doAfterResponse(String remoteAddr, RemotingTransporter request, RemotingTransporter response) {

    }
}
