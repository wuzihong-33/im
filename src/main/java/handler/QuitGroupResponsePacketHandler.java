package handler;

import entity.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class QuitGroupResponsePacketHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        if (quitGroupResponsePacket.isSuccess()) {
            System.out.println("退出群成功，groupId: " + quitGroupResponsePacket.getGroupId());
        } else {
            System.out.println("退出群失败，reason: " + quitGroupResponsePacket.getReason());
        }
    }
}
