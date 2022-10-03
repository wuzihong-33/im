package handler;

import coder.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.LoginResponsePacket;
import protocol.Packet;
import utils.LoginUtils;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    /**
     * 可以看到新老版本的比较
     * 1、不再需要做类型转换
     * 2、由于将handler拆开了，因此不再需要做解码处理（如果不往流水线添加encode、decode，会发生什么？）
     * @param ctx
     * @param loginResponsePacket
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        if (loginResponsePacket.isSuccess()) {
            LoginUtils.markAsLogin(ctx.channel());
            System.out.println("client login success");
        } else {
            System.out.println("client login fail, reason: " + loginResponsePacket.getReason());
        }
    }
}
