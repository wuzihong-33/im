package protocol;

import marks.Command;

public class LoginRequestPacket extends Packet {
    private final String userId;
    private final String userName;
    private final String passWord;

    public LoginRequestPacket(String userId, String userName, String passWord) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
    }

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

}
