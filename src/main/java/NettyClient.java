import coder.PacketCodeC;
import handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import protocol.MessageRequestPacket;
import utils.LoginUtils;

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
//                        channel.pipeline().addLast(new handler.FirstClientHandler());
                        channel.pipeline().addLast(new ClientHandler());
                    }
                });
        connect(bootstrap, "localhost", 8080, MAX_RETRY);
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                // 在控制台线程中，判断只要当前Channel是登录状态，就允许控制台输入消息。
                if (LoginUtils.hasLogin(channel)) {
                    System.out.println("输入消息发送服务端：");
                    Scanner sc = new Scanner(System.in);
                    String msg = sc.nextLine();

                    MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                    messageRequestPacket.setMsg(msg);

                    ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(messageRequestPacket);
                    channel.writeAndFlush(byteBuf);
//                    Thread.interrupted();
                }
            }
        }).start();
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap
                .connect(host, port)
                .addListener(future -> {
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



}
