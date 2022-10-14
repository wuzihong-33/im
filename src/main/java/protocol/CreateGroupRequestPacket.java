package protocol;

import marks.Command;

import java.util.LinkedList;
import java.util.List;

public class CreateGroupRequestPacket extends Packet {
    private List<String> userIds = new LinkedList<>();

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
