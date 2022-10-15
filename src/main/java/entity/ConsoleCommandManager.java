package entity;

import exception.CommandNotFindException;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleCommandManager {
    private static Map<String, ConsoleCommand> consoleCommandMap;

    static {
        consoleCommandMap = new HashMap<>();

        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
    }

    public ConsoleCommandManager() {
    }

    public static ConsoleCommand getConsoleCommand(String consoleCommand) throws CommandNotFindException {
        ConsoleCommand command = consoleCommandMap.get(consoleCommand);
        if (command == null) {
            throw new CommandNotFindException();
        }
        return command;
    }

//    @Override
//    public void execute(Scanner sc, Channel channel) {
//        String command = sc.nextLine();
//        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
//        if (consoleCommand != null) {
//            consoleCommand.execute(sc, channel);
//        } else {
//            System.out.println("输入的指令无法识别！");
//        }
//    }
}
