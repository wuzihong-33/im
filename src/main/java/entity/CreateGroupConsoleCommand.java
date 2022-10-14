package entity;

import Constants.Constants;
import io.netty.channel.Channel;
import protocol.CreateGroupRequestPacket;

import java.util.Arrays;
import java.util.Scanner;

public class CreateGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void execute(Scanner sc, Channel channel) {
        System.out.println("【创建群聊】输入userId，userId间用,分隔开: ");
        String userIds = sc.nextLine();
//        System.out.println("");
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        createGroupRequestPacket.setUserIds(Arrays.asList(userIds.split(Constants.USER_ID_SPLITER)));
        channel.writeAndFlush(createGroupRequestPacket);
    }


}
