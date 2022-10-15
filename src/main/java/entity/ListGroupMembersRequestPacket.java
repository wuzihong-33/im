package entity;

import protocol.Packet;

public class ListGroupMembersRequestPacket extends Packet {
    private String groupId;
    private Session session;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public Byte getCommand() {
        return null;
    }
}
