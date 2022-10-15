package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.Packet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对应用程序来说，每次decode出来一个指令对象后，其实都只会在一个指令Handler上进行处理。因此，可以把这么多的指令Handler压缩为一个Handler
 */
public class IMHandler extends SimpleChannelInboundHandler<Packet> {
    public static final IMHandler INSTANCE = new IMHandler();
    private final Map<String, SimpleChannelInboundHandler<? extends Packet>> handlers;
    private IMHandler() {
        handlers = new ConcurrentHashMap<>();
//        handlers.put();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {

    }
}
