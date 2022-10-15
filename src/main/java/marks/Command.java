package marks;

public interface Command {
    // 登录
    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;

    // 发送消息
    Byte MESSAGE_REQUEST  = 3;
    Byte MESSAGE_RESPONSE = 4;

    // 创建群
    Byte CREATE_GROUP_REQUEST = 5;
    Byte CREATE_GROUP_RESPONSE = 6;

    // 加入群
    Byte JOIN_GROUP_REQUEST = 7;
    Byte JOIN_GROUP_RESPONSE = 8;

    // 退出群
    Byte QUIT_GROUP_REQUEST = 9;
    Byte QUIT_GROUP_RESPONSE = 10;

    // 加入群
    Byte LIST_GROUP_MEMBERS_REQUEST = 11;
    Byte LIST_GROUP_MEMBERS_RESPONSE = 12;

    // 发送群消息
    Byte GROUP_MESSAGE_REQUEST = 13;
    Byte GROUP_MESSAGE_RESPONSE = 14;



}
