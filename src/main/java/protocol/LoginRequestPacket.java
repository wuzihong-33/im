package protocol;

import marks.Command;

public class LoginRequestPacket extends Packet {
    private String userName;
    private String passWord;

    public LoginRequestPacket(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

}
