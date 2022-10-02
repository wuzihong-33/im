package utils;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import marks.Attributes;

public class LoginUtils {
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }
    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;// 如果有标志位，不管标志位的值是什么，都表示已经成功登录
    }
}
