import blockchain.Block;
import org.junit.Test;

/**
 * Created by zhuran on 2018/7/17 0017
 */
public class TestBlock {
    @Test
    public void Test() {
        Block block = Block.getInstace();
        block.addBlock("zhuran","zhuran");
        block.addBlock("laoda","6d59c1a54470a96d3665fb2ba1bd8ae8f7c51e2823ec6345660bf605742e0a3f");
        block.say();
    }
}
