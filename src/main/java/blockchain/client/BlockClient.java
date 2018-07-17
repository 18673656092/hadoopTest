package blockchain.client;

import blockchain.BlockDate;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static jdk.internal.util.xml.XMLStreamWriter.DEFAULT_ENCODING;

/**
 * Created by zhuran on 2018/7/17 0017
 */
public class BlockClient {

    private String ip;

    private int port;

    public BlockClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void start() {

        EventLoopGroup client = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(client).channel(NioSocketChannel.class).handler(new BlockClientInitalizer());
        try {
            ChannelFuture cf = b.connect(ip, port).sync();
            BlockDate bd = new BlockDate();
            bd.setDate("zhangsan");
            bd.setKey("zhuran");
            try {
                cf.channel().writeAndFlush(writeToStr(bd));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("挖矿请求发送成功");
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            client.shutdownGracefully();
        }
    }

    private String writeToStr(Object obj) throws IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
        objOut.writeObject(obj);
        return byteOut.toString("ISO-8859-1");
    }

    public static void main(String[] args) {
        BlockClient blockClient = new BlockClient("127.0.0.1", 8080);
        blockClient.start();
    }
}
