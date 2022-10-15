package utils;

import entity.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import marks.Attributes;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtils {
    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();
    private static final Map<String, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();

    public static ChannelGroup getChannelGroup(String groupId) {
        return channelGroupMap.get(groupId);
    }

    public static void bindSession(Session session, Channel channel) {
        userIdChannelMap.put(session.getUserId(), channel);
        System.out.println("userName: " + session.getUserName() + "===" + "userId" + session.getUserId() + "bind success");
        System.out.println(userIdChannelMap.toString());
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void recordChannelGroup(String groupId, ChannelGroup channelGroup) {
        channelGroupMap.put(groupId, channelGroup);
        System.out.println("groupId: " + groupId + "  record success");
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            userIdChannelMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }
    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }


    // 根据channel获得session
    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    // 根据userId获取channel
    public static Channel getChannel(String userId) {
        return userIdChannelMap.get(userId);
    }
}
