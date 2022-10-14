import coder.PacketDecoder;
import coder.PacketEncoder;
import entity.ConsoleCommand;
import entity.ConsoleCommandManager;
import exception.CommandNotFindException;
import handler.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import protocol.LoginRequestPacket;
import protocol.MessageRequestPacket;
import utils.LoginUtils;
import utils.SessionUtils;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NettyClient {
    private final static int MAX_RETRY = 10;
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
//                        channel.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
//                        channel.pipeline().addLast(new StickOrHalfPackageHandler()); // 粘包半包演示
                        // 下列都是inbound，处理顺序和addLast顺序一致
//                        channel.pipeline().addLast(new AutoLoginHandler()); // 连接建立后客户端自动发送登录请求
                        channel.pipeline().addLast(new PacketDecoder()); // 收到消息，统一需要对packet解码成obj对象
                        channel.pipeline().addLast(new CreateGroupResponseHandler()); // 收到消息，统一需要对packet解码成obj对象
                        channel.pipeline().addLast(new LoginResponseHandler());
                        channel.pipeline().addLast(new MessageResponseHandler());
                        channel.pipeline().addLast(new PacketEncoder()); // 发送消息，统一需要对packet编码
                    }
                });
        connect(bootstrap, "localhost", 8080, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap
                .connect(host, port)
                .addListener(future -> { // 疑惑：这里的监听器和channelActive，谁先被调用？  答案：这里的回调监听优先调用，然后才是channelActive
                    if (future.isSuccess()) {
                        System.out.println("client connect server success！");
                        Channel channel = ((ChannelFuture)future).channel();
                        startConsoleThread(channel);
                    } else if (retry == 0) {
                        System.out.println("重试次数用完，连接失败！");
                    } else {
                        int order = (MAX_RETRY - retry) + 1; // 第几次重试
                        int delay = 1 << order; // 本次重连的间隔
                        System.out.println(new Date() + "连接失败，第" + order + "次重连....");
                        // bootstrap.config().group()返回的就是我们在一开始配置的线程模型workerGroup，调用workerGroup的schedule方法即可实现定时任务逻辑。
                        bootstrap.config().group().schedule(()->connect(bootstrap, host, port, retry), delay, TimeUnit.SECONDS);
                    }
                });
    }

    private static void startConsoleThread(Channel channel) {
        Scanner sc = new Scanner(System.in);
        new Thread(() -> {
            while (!Thread.interrupted()) {
                // 在控制台线程中，判断只要当前Channel是登录状态，就允许控制台输入消息。
                if (!LoginUtils.hasLogin(channel)) {
                    System.out.println("请输入用户名登录：");
                    String userName = sc.nextLine();
//                    MessageRequestPacket messageRequestPacket = new MessageRequestPacket(msg);
//                    ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(messageRequestPacket);
//                    channel.writeAndFlush(byteBuf);
                    LoginRequestPacket loginRequestPacket = new LoginRequestPacket(userName, "pwd");
                    channel.writeAndFlush(loginRequestPacket);
                    waitForLoginResponse();
                } else {
                    System.out.println("指令列表：【sendToUser】:1, 【logout】: 2, 【createGroup】: 3" + "选择你需要的指令：");
                    Integer commandIndex = Integer.valueOf(sc.nextLine());

                    String command ;
                    switch (commandIndex) {
                        case 1:
                            command = "sendToUser";
                            break;
                        case 2:
                            command = "logout";
                            break;
                        case 3:
                            command = "createGroup";
                            break;
                        default:
                            System.out.println("commandIndex un valid, specific 1");
                            command = "sendToUser";
                    }
                    ConsoleCommand consoleCommand;
                    try {
                        consoleCommand = ConsoleCommandManager.getConsoleCommand(command);
                        consoleCommand.execute(sc, channel);
                    } catch (CommandNotFindException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





}
