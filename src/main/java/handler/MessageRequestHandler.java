package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.MessageRequestPacket;
import protocol.MessageResponsePacket;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        // 处理消息逻辑
//        MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
        System.out.println("server receive client msg: " + messageRequestPacket.getMsg());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMsg("server success receive msg: " + messageRequestPacket.getMsg());
        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
