package protocol;

public abstract class Packet {
    // 协议版本号  只有协议升级时会使用到
    private byte version = 1;

    // 获取指令
    public abstract Byte getCommand();

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }
}

