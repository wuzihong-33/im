package handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class EchoHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        String str = byteBuf.toString(Charset.forName("utf-8"));
        System.out.println("server print: " + str);

        // 粘包：server print: 10年河东，十年河西，哈哈哈10年河东，十年河西
        // 半包：server print: ，哈哈哈
    }
}
