package handler;

import coder.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class Spliter extends LengthFieldBasedFrameDecoder {
    private static final int LENGTH_FIELD_OFFSET = 7;
    private static final int LENGTH_FIELD_LENGTH = 4;

    public Spliter(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    // 为什么可以在decode()方法中写这段逻辑？
    // 因为在decode()方法中，每次第二个参数in传递进来的时候，均是一个数据包的开头。
    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        int magicNumber = in.getInt(in.readerIndex());
        if (magicNumber != PacketCodeC.MAGIC_NUMBER) {
            ctx.channel().close();
            // 疑惑：为什么会close 3次呢？
            // 调用了3次，
//            close channel, magic number: 1193046
//            close channel, magic number: 1193046
//            close channel, magic number: 1193046
            System.out.println("close channel, magic number: " + magicNumber);
            return null;
        }
        return super.decode(ctx, in);
    }
}
