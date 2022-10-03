package handler;

import coder.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocol.*;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            LoginResponsePacket loginResponsePacket = LoginResponsePacket.success();
            loginResponsePacket.setVersion(packet.getVersion());
            // 处理登录逻辑：登录校验
            if (validLogin(loginRequestPacket)) {
                // 写入响应
                loginResponsePacket.setSuccess(true);
            } else {
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("登录校验失败");
            }
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        } else if (packet instanceof MessageRequestPacket) {
            // 处理消息逻辑
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            System.out.println("server receive client msg: " + messageRequestPacket.getMsg());
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMsg("server message response");
        }

    }

    private boolean validLogin(LoginRequestPacket packet) {
        return true;
    }
}
