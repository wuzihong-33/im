package entity;

import Constants.Constants;
import io.netty.channel.Channel;
import protocol.CreateGroupRequestPacket;
import protocol.MessageRequestPacket;

import java.util.Arrays;
import java.util.Scanner;

public class SendToUserConsoleCommand implements ConsoleCommand {

    @Override
    public void execute(Scanner sc, Channel channel) {
        System.out.println("输入toUserId：");
        String toUserId = sc.nextLine();
        System.out.println("输入msg：");
        String msg = sc.nextLine();
        MessageRequestPacket messageRequestPacket = new MessageRequestPacket(toUserId, msg);
        channel.writeAndFlush(messageRequestPacket);
    }
}
