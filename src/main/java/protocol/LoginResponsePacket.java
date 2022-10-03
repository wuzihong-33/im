package protocol;

import marks.Command;

public class LoginResponsePacket extends Packet {
    private boolean success;
    private String reason;

    public LoginResponsePacket(boolean success, String reason) {
        this.success = success;
        this.reason = reason;
    }
    public LoginResponsePacket(boolean success) {
        this.success = success;
    }

    public static LoginResponsePacket success() {
        return new LoginResponsePacket(true);
    }
    public static LoginResponsePacket fail(String reason) {
        return new LoginResponsePacket(false, reason);
    }


    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
