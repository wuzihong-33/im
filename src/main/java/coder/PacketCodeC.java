package coder;

import Serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import marks.Command;
import protocol.*;

/**
 * 老版本
 * encoder和decoder一体，未继承ByteToMessageDecoder和MessageToByteEncoder
 */
public class PacketCodeC {
    public static PacketCodeC INSTANCE = new PacketCodeC();
    public static final int MAGIC_NUMBER = 0x12345678;
    private PacketCodeC(){
    }
    public ByteBuf encode(Packet packet) {
        // ioBuffer()方法会返回适配IO读写相关的内存，它会尽可能创建一个直接内存。
        // 直接内存可以理解为不受JVM堆管理的内存空间，写到IO缓冲区的效果更高。
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 协议：
        // 魔数 - 版本号 - 序列化方式 - 指令 - 长度 - 内容
        byteBuf.writeInt(0x123456);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }
    public void encode(ByteBuf byteBuf, Packet packet) {
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 协议：
        // 魔数 - 版本号 - 序列化方式 - 指令 - 长度 - 内容
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }
    public Packet decode(ByteBuf byteBuf) {
        // 跳过魔数
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 序列化算法标识
        byte serializerAlgorithm = byteBuf.readByte();
        // 指令
        byte command = byteBuf.readByte();
        // 数据长度
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> packetType = getRequestType(command);
        Serializer serializer = getSerializerAlgorithm(serializerAlgorithm);
        if (packetType == null || serializer == null) {
            return null;
        }
        return serializer.deserialize(packetType, bytes);

    }

    private Class<? extends Packet> getRequestType(byte command) {
        if (command == Command.LOGIN_REQUEST) {
            return LoginRequestPacket.class;
        } else if (command == Command.LOGIN_RESPONSE) {
            return LoginResponsePacket.class;
        } else if (command == Command.MESSAGE_REQUEST) {
            return MessageRequestPacket.class;
        } else if (command == Command.MESSAGE_RESPONSE) {
            return MessageResponsePacket.class;
        }
        return LoginRequestPacket.class;
    }
    private Serializer getSerializerAlgorithm(byte serializerAlgorithm) {
//        if (command == protocol.marks.Command.LOGIN_REQUEST) {
//            return protocol.protocol.LoginRequestPacket.class;
//        }
        return Serializer.DEFAULT;
    }
}
