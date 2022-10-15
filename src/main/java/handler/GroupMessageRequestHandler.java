package handler;

import entity.GroupMessageRequestPacket;
import entity.GroupMessageResponsePacket;
import entity.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import utils.SessionUtils;

public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket groupMessageRequestPacket) throws Exception {
        String groupId = groupMessageRequestPacket.getGroupId();
        GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();

        groupMessageResponsePacket.setFromGroupId(groupId);
        groupMessageResponsePacket.setFromUserId(SessionUtils.getSession(ctx.channel()).getUserId());
        groupMessageResponsePacket.setMsg(groupMessageRequestPacket.getMsg());

        // 拿到群聊
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        channelGroup.writeAndFlush(groupMessageResponsePacket);

    }
}
