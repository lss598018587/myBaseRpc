package com.wm.remoting.model;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import static com.wm.common.protocal.LaopopoProtocol.*;

/**
 * 
 * @author BazingaLyn
 * @description
 * @time
 * @modifytime
 */
@SuppressWarnings("deprecation")
public class Heartbeats {

    private static final ByteBuf HEARTBEAT_BUF;
    
    static {
        ByteBuf buf = Unpooled.buffer(HEAD_LENGTH);
        buf.writeShort(MAGIC);
        buf.writeByte(HEARTBEAT);
        buf.writeByte(0);
        buf.writeLong(0);
        buf.writeInt(0);
        //Unpooled.unmodifiableBuffer创建对象，所有的写请求都将被禁止
        HEARTBEAT_BUF = Unpooled.unmodifiableBuffer(Unpooled.unreleasableBuffer(buf));
    }

    /**
     * Returns the shared heartbeat content.
     */
    public static ByteBuf heartbeatContent() {
        return HEARTBEAT_BUF.duplicate();
    }
}
