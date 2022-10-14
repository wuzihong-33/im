package entity;

import io.netty.channel.Channel;

import java.util.Scanner;

public interface ConsoleCommand {
    void execute(Scanner sc, Channel channel);
}
