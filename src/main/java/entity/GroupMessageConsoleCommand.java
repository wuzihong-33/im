package entity;

import io.netty.channel.Channel;
import protocol.MessageRequestPacket;

import java.util.Scanner;

public class GroupMessageConsoleCommand implements ConsoleCommand{
    @Override
    public void execute(Scanner sc, Channel channel) {
        System.out.println("输入groupId：");
        String groupId = sc.nextLine();
        System.out.println("输入msg：");
        String msg = sc.nextLine();
        GroupMessageRequestPacket groupMessageRequestPacket = new GroupMessageRequestPacket();
        groupMessageRequestPacket.setGroupId(groupId);
        groupMessageRequestPacket.setMsg(msg);
        channel.writeAndFlush(groupMessageRequestPacket);
    }
}
