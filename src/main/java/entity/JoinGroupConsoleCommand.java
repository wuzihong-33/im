package entity;

import io.netty.channel.Channel;
import marks.Command;
import protocol.JoinGroupRequestPacket;

import java.util.Scanner;

public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void execute(Scanner sc, Channel channel) {
        System.out.println("输入group id，加入群聊");
        String groupId = sc.nextLine();
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket(groupId);
        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
