package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.CreateGroupResponsePacket;

public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        if (createGroupResponsePacket.isSuccess()) {
            System.out.println(createGroupResponsePacket.toString());
        } else {
            System.out.println("client login fail, reason: " + createGroupResponsePacket.getReason());
        }
    }
}
