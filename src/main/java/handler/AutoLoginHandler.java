package handler;

import coder.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocol.*;
import utils.LoginUtils;

import java.util.UUID;

// 连接一旦建立，就自动进行登录请求
public class AutoLoginHandler extends ChannelInboundHandlerAdapter {
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        // 连接建立后开始登录
//        System.out.println("client try login");
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket(UUID.randomUUID().toString(), "flash", "pwd");
//
//        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(loginRequestPacket);
//        ctx.channel().writeAndFlush(byteBuf);
//    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        // 客户端处理响应
//        ByteBuf byteBuf = (ByteBuf)msg;
//        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
//        if (packet instanceof LoginResponsePacket) {
//            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
//            if (loginResponsePacket.isSuccess()) {
//                // 在客户端登录成功之后，给客户端绑定登录成功的标志位。
//                LoginUtils.markAsLogin(ctx.channel());
//                System.out.println("client login success");
//            } else {
//                System.out.println("client login fail, reason: " + loginResponsePacket.getReason());
//            }
//        } else if (packet instanceof MessageResponsePacket){
//            // 处理消息逻辑
//            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
//            System.out.println("client receive server msg: " + messageResponsePacket.getMsg());
//        }
//    }
}
