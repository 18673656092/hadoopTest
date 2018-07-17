package blockchain.server;

import blockchain.Block;
import blockchain.BlockDate;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;


/**
 * Created by zhuran on 2018/7/17 0017
 */
public class BlockServerHandler extends ChannelInboundHandlerAdapter {

    private Block block = null;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "向服务端发起挖矿请求" );
        BlockDate bd = (BlockDate)deserializeToObject((String) msg);
        System.out.println("收到来自Block客户端的挖矿请求");
        if(block.addBlock(bd.getDate(),bd.getKey())){
            System.out.println("挖矿成功");
            ctx.write("挖矿成功");
            ctx.flush();
        }else {
            System.out.println("挖矿失败");
            ctx.write("挖矿失败");
            ctx.flush();
        }
        System.out.println("当前整个区块链数据:");
        block.say();
    }

    private Object deserializeToObject(String str) throws Exception{
        ByteArrayInputStream byteIn = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
        ObjectInputStream objIn = new ObjectInputStream(byteIn);
        Object obj =objIn.readObject();
        return obj;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        block = Block.getInstace();
        System.out.println("新的客户端Block连接");
    }
}
