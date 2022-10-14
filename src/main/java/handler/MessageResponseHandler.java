package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.MessageResponsePacket;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        // 把包的信息打印一下
        System.out.println("rsv" + messageResponsePacket.toString());
    }
}
