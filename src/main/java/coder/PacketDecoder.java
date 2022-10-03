package coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 新版本
 */
public class PacketDecoder extends ByteToMessageDecoder {
    // 1、传进来就是ByteBuf类型，不需要强制转换；
    // 2、向List里面添加解码后的结果对象，就可以自动实现结果向下一个Handler传递
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(PacketCodeC.INSTANCE.decode(byteBuf));
    }
}
