package handler;

import coder.PacketCodeC;
import entity.Session;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.LoginRequestPacket;
import protocol.LoginResponsePacket;
import utils.LoginUtils;
import utils.SessionUtils;

import java.util.concurrent.atomic.AtomicInteger;

// 对LoginRequestPacket包的处理
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    private static AtomicInteger userIdBuilder = new AtomicInteger(0);
    /**
     * 单聊实现：登录的时候保存会话信息，登出的时候删除会话信息。
     * 可以看到新老版本的比较：
     * 1、写channel时不再需要将java对象 encode 成ByteBuf
     * 2、参数传进来就已经是LoginRequestPacket，不再需要做类型转换
     * @param ctx
     * @param loginRequestPacket
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        String userName = loginRequestPacket.getUserName();
        String userId = String.valueOf(userName.hashCode());
        SessionUtils.bindSession(new Session(userId, userName), ctx.channel());
        LoginResponsePacket loginResponsePacket = LoginResponsePacket.success();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
//        LoginUtils.markAsLogin(ctx.channel());
        ctx.channel().writeAndFlush(loginResponsePacket);

        // 登录逻辑判断，写回响应到channel
//        if (!validLogin(loginRequestPacket)) { // 从数据库中验证账号密码

//        } else {
//            loginResponsePacket = LoginResponsePacket.fail("登录校验失败! ");
//        }
    }
    private boolean validLogin(LoginRequestPacket packet) {
        return true;
    }

}
