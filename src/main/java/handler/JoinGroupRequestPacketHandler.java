package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import protocol.JoinGroupRequestPacket;
import protocol.JoinGroupResponsePacket;
import utils.SessionUtils;

public class JoinGroupRequestPacketHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        String groupId = joinGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        if (channelGroup != null) {
            channelGroup.add(ctx.channel());
            // 构建响应
            joinGroupResponsePacket.setSuccess(true);
            joinGroupResponsePacket.setGroupId(groupId);
        } else {
            joinGroupResponsePacket.setSuccess(false);
            joinGroupResponsePacket.setReason("group不存在，请输入正确的group");
        }
        ctx.channel().writeAndFlush(joinGroupResponsePacket);
    }
}
