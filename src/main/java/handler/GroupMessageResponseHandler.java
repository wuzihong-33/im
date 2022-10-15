package handler;

import entity.GroupMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket groupMessageResponsePacket) throws Exception {
        if (groupMessageResponsePacket.isSuccess()) {
            System.out.println("收到groupId: " + groupMessageResponsePacket.getFromGroupId() + ", userId: " + groupMessageResponsePacket.getFromUserId() + "发来的消息：" + groupMessageResponsePacket.getMsg());
        } else {
            System.out.println("groupMessageResponsePacket fail： " + groupMessageResponsePacket.getReason());
        }
    }
}
