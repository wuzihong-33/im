package handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import protocol.CreateGroupRequestPacket;
import protocol.CreateGroupResponsePacket;
import utils.IDUtils;
import utils.SessionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        // 还有一个持久化的事情需要做
        List<String> userIds = createGroupRequestPacket.getUserIds();
        // 创建一个channel分组
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        List<String> userNameList = new ArrayList<>();

        // 筛选出在线的发送
        for (String userId : userIds) {
            Channel channel = SessionUtils.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNameList.add(SessionUtils.getSession(channel).getUserName());
            }
        }
        // 创建群聊结果
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setGroupId(IDUtils.getId());
        createGroupResponsePacket.setUserNameList(userNameList);
        createGroupResponsePacket.setSuccess(true);

        // 调用channelGroup方法，给每个客户端都发送一条消息
        channelGroup.writeAndFlush(createGroupResponsePacket);

    }
}
