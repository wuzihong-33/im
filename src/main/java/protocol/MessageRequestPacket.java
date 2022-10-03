package protocol;

import marks.Command;

public class MessageRequestPacket extends Packet {
    private String msg;

    public MessageRequestPacket(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
