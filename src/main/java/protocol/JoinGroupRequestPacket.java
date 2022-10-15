package protocol;

public class JoinGroupRequestPacket extends Packet {
    private String groupId;

    public JoinGroupRequestPacket(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public Byte getCommand() {
        return null;
    }
}
