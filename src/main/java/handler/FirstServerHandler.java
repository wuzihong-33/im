package handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        String strMsg = buf.toString(Charset.forName("utf-8"));
        System.out.println(new Date() + "服务器收到客户端消息: " + strMsg);

        ByteBuf out = getByteBuf(ctx);
        ctx.channel().writeAndFlush(out);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf buf = ctx.alloc().buffer();
        byte[] bytes = "i'm server, hello client".getBytes();
        buf.writeBytes(bytes);
        return buf;
    }

}
