package protocol;

import marks.Command;

public class MessageRequestPacket extends Packet {
    private String toUserId;
    private String msg;

    public String getToUserId() {
        return toUserId;
    }


    public MessageRequestPacket(String toUserId, String msg) {
        this.toUserId = toUserId;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MessageRequestPacket{" +
                "toUserId='" + toUserId + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
