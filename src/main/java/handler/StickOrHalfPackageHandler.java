package handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class StickOrHalfPackageHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10000000; i++) {
            byte[] bytes = "10年河东，十年河西，哈哈哈".getBytes(Charset.forName("utf-8"));
            ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
            byteBuf.writeBytes(bytes);
            ctx.channel().writeAndFlush(byteBuf);
        }
    }
}
