package handler;

import Serializer.Serializer;
import coder.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocol.MessageRequestPacket;

import java.nio.charset.Charset;

public class StickOrHalfPackageHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("StickOrHalfPackageHandler invoke channelActive function");
//        for (int i = 0; i < 100; i++) {
            // 疑惑：这里是将byteBuf当成协议了？那protocol和byteBuf的关系呢？
//            byte[] bytes = "10年河东，十年河西，哈哈哈".getBytes(Charset.forName("utf-8"));
//            MessageRequestPacket messageRequestPacket = new MessageRequestPacket("10年河东，十年河西，哈哈哈");
//            ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(messageRequestPacket);
//            ctx.channel().writeAndFlush(byteBuf);
//        }
        System.out.println("StickOrHalfPackageHandler finish");
    }
}
