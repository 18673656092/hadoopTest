import blockchain.Block;
import org.junit.Test;

/**
 * Created by zhuran on 2018/7/17 0017
 */
public class TestBlock {
    @Test
    public void Test() {
        Block block = Block.getInstace();
        block.addBlock("send 1 BTC to C","zhuran");
        block.addBlock("send 2 BTC to D","zhuran");
        block.addBlock("send 1 BTC to B","zhuran");
        block.say();
    }
}
