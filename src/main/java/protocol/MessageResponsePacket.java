package protocol;

import marks.Command;

public class MessageResponsePacket extends Packet {
    private String fromUserId;
    private String fromUserName;
    private String msg;

    public MessageResponsePacket() {
    }

    public MessageResponsePacket(String fromUserId, String fromUserName, String msg) {
        this.fromUserId = fromUserId;
        this.fromUserName = fromUserName;
        this.msg = msg;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MessageResponsePacket{" +
                "fromUserId='" + fromUserId + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
