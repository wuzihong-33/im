package protocol;

import marks.Command;

import java.util.List;

public class CreateGroupResponsePacket extends Packet {
    private boolean success;
    private String reason;
    private String groupId;
    private List<String> userNameList;

    public CreateGroupResponsePacket() {
    }


    public boolean isSuccess() {
        return success;
    }

    public List<String> getUserNameList() {
        return userNameList;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setUserNameList(List<String> userNameList) {
        this.userNameList = userNameList;
    }

    @Override
    public String toString() {
        return "CreateGroupResponsePacket{" +
                "success=" + success +
                ", groupId='" + groupId + '\'' +
                ", userNameList=" + userNameList +
                '}';
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
