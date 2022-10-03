package handler;

import coder.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.LoginRequestPacket;
import protocol.LoginResponsePacket;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    /**
     * 可以看到新老版本的比较：
     * 1、写channel时不再需要将java对象 encode 成ByteBuf
     * 2、参数传进来就已经是LoginRequestPacket，不再需要做类型转换
     * @param ctx
     * @param loginRequestPacket
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket;
        // 登录逻辑判断，写回响应到channel
        if (validLogin(loginRequestPacket)) {
            loginResponsePacket = LoginResponsePacket.success();
            loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        } else {
            loginResponsePacket = LoginResponsePacket.fail("登录校验失败! ");
        }
        ctx.channel().writeAndFlush(loginResponsePacket);
    }
    private boolean validLogin(LoginRequestPacket packet) {
        return true;
    }

}
