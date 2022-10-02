import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NettyClient {
    private final static int MAX_RETRY = 10;
    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap
                .connect(host, port)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println("连接成功！");
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


    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
//                        channel.pipeline().addLast(new FirstClientHandler());
                        channel.pipeline().addLast(new ClientHandler());
                    }
                });
        connect(bootstrap, "localhost", 8080, MAX_RETRY);



    }
}
