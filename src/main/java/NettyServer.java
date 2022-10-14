import coder.PacketDecoder;
import coder.PacketEncoder;
import handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class NettyServer {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)   // 指定io模型
                .childHandler(new ChannelInitializer<NioSocketChannel>() { // 初始化通道
                    protected void initChannel(NioSocketChannel channel) throws Exception {
//                        channel.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
//                        channel.pipeline().addLast(new Spliter(Integer.MAX_VALUE, 7, 4));
//                        channel.pipeline().addLast(new EchoHandler());
//                        channel.pipeline().addLast(new ServerHandler());
                        // 下列都是inbound，处理顺序和addLast顺序一致
                        channel.pipeline().addLast(new PacketDecoder());
                        channel.pipeline().addLast(new LoginRequestHandler());
//                        channel.pipeline().addLast(new AuthHandler());
                        channel.pipeline().addLast(new MessageRequestHandler());
                        channel.pipeline().addLast(new PacketEncoder());
                    }
                });
        // childOption()方法可以给每个连接都设置一些TCP参数。
        serverBootstrap
                .childOption(ChannelOption.SO_KEEPALIVE, true) // 是否开启tcp底层心跳，true表示开启
                .childOption(ChannelOption.TCP_NODELAY, true);// 是否开启nagle算法，true表示关闭，false开启。通俗地说，如果要求高实时性，有数据发送时就马上发送，就设置为关闭；如果需要减少发送次数，减少网络交互，就设置为开启。

        NettyServer.bind(serverBootstrap, 8080);
    }
    // 当绑定端口失败时，尝试自增端口号，直至成功
    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap
                .bind(port)  // 监听端口  异步方法  返回ChannelFuture
                .addListener(new GenericFutureListener<Future<? super Void>>() { // 给返回的ChannelFuture加入监听器
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (future.isSuccess()) {
                            System.out.println("绑定端口: " + port + "成功");
                        } else {
                            System.out.println("绑定端口: " + port + "失败" + "尝试绑定端口: " + port+1);
                            bind(serverBootstrap, port+1);
                        }
                    }
                });
    }
}
