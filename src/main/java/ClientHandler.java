import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.UUID;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 连接建立后开始登录
        System.out.println("客户端开始登录");
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("flash");
        loginRequestPacket.setPassWord("pwd");

        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(loginRequestPacket);
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 客户端处理响应
        ByteBuf byteBuf = (ByteBuf)msg;
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        System.out.println("client, 收到响应");
        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            if (loginResponsePacket.isSuccess()) {
                System.out.println("客户端登陆成功");
            } else {
                System.out.println("客户端登录失败，失败原因：" + loginResponsePacket.getReason());
            }
        } else if (packet instanceof MessageResponsePacket){

        }
    }
}
