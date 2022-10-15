package handler;

import entity.QuitGroupRequestPacket;
import entity.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import utils.SessionUtils;

public class QuitGroupRequestPacketHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        String groupId = quitGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();
        if (channelGroup == null) {
            quitGroupResponsePacket.setSuccess(false);
            quitGroupResponsePacket.setReason("群不存在");
        } else {
            channelGroup.remove(ctx.channel());
            quitGroupResponsePacket.setSuccess(true);
        }
        quitGroupRequestPacket.setGroupId(quitGroupRequestPacket.getGroupId());
        ctx.channel().writeAndFlush(quitGroupResponsePacket);

    }
}
