package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.JoinGroupResponsePacket;

public class JoinGroupResponsePacketHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket joinGroupResponsePacket) throws Exception {
        if (joinGroupResponsePacket.isSuccess()) {
            System.out.println("加入群聊成功，groupId：" + joinGroupResponsePacket.getGroupId());
        } else {
            System.out.println("加入群聊失败");
        }
    }
}
