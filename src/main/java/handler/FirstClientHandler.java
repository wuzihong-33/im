package handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端写入数据");
        ByteBuf buf = getByteBuf(ctx); // 获取写死的固定消息
        ctx.channel().writeAndFlush(buf); // 向通道写入数据
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        String strMsg = buf.toString(Charset.forName("utf-8"));
        System.out.println(new Date() + "客户端收到服务器消息: " + strMsg);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf buf = ctx.alloc().buffer();
        byte[] bytes = "i'm client, hello server".getBytes();
        buf.writeBytes(bytes);
        return buf;
    }


}
