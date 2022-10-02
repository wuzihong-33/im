public class MessageRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
