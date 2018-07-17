package blockchain.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by zhuran on 2018/7/17 0017
 */
public class BlockServer {

    private int port;

    public BlockServer(int port){
        this.port = port;
    }

    public void start(){
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(boss,work).channel(NioServerSocketChannel.class).childHandler(new BlockServerInitalizer());
        try {
            ChannelFuture cf = sb.bind(port).sync();
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        BlockServer blockServer = new BlockServer(8080);
        blockServer.start();
    }
}
