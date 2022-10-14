package handler;

import entity.Session;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.MessageRequestPacket;
import protocol.MessageResponsePacket;
import utils.SessionUtils;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        System.out.println("server rsv, " + messageRequestPacket.toString());
        // 1、获得消息发送方的会话信息
        Session session = SessionUtils.getSession(ctx.channel()); // 这个channel从发送方那个channel
        System.out.println("server get session from mem, " + session.toString());

        // 2、将消息转发：fromUserId-fromUserName-msg
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket(session.getUserId(), session.getUserName(), messageRequestPacket.getMsg());


        // 3、获取toUserId，然后发送出去
        Channel toUSerChannel = SessionUtils.getChannel(messageRequestPacket.getToUserId());

        if (toUSerChannel != null && SessionUtils.hasLogin(toUSerChannel)) {
            System.out.println("write and flash, " + messageResponsePacket.toString());
            toUSerChannel.writeAndFlush(messageResponsePacket);
        } else {
            System.out.println("[" + messageResponsePacket.getFromUserName() + "]" + "不在线，发送送失败");
        }
        // 还有一个bug，为什么接收方收到数据回显是null
        // 另一个客户端也打印了，但为什么本身也打印了？不应该回显啊
//        ctx.channel().writeAndFlush(messageResponsePacket);// 我他妈是傻逼
    }
    // 打印并简单返回

    private MessageResponsePacket simpleEcho(MessageRequestPacket messageRequestPacket) {
        System.out.println("server receive client msg: " + messageRequestPacket.getMsg());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMsg("server success receive msg: " + messageRequestPacket.getMsg());
        return messageResponsePacket;
    }
}
