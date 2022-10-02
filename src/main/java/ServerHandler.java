import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        System.out.println("server: " + "收到协议信息：" + packet);

        if (packet instanceof LoginRequestPacket) {
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            // 处理登录逻辑：登录校验
            if (validLogin((LoginRequestPacket) packet)) {
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
        }

    }

    private boolean validLogin(LoginRequestPacket packet) {
        return true;
    }
}
