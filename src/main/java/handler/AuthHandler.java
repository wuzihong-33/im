package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import utils.LoginUtils;


//如果客户端已经登录成功，那么在每次处理客户端数据之前，都要经历这么一段逻辑。
// 比如，平均每次用户登录之后发送100次消息，其实剩余的99次身份校验逻辑都是没有必要的，因为只要连接未断开，只要客户端成功登录过，后续就不需要再进行客户端的身份校验。
// 在实际生产环境中，身份认证逻辑可能会更复杂。我们需要寻找一种途径来避免资源与性能的浪费，使用ChannelHandler的热插拔机制完全可以做到这一点。
// 继承ChannelInboundHandlerAdapter的好处是，表明它可以处理所有类型的数据。
public class AuthHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!LoginUtils.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            // 一行代码实现逻辑的删除
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("check");
    }
}
