package blockchain;

import org.apache.commons.codec.binary.Hex;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

/**
 * Created by zhuran on 2018/7/17 0017
 */
public class Block implements Serializable {

    private static LinkedList<Block> linkedListBlock;
    private String perHash;
    private String hash;
    private String data;

    private static Block block = null;

    public static Block getInstace() {
        if (block == null) {
            block = new Block();
            linkedListBlock = new LinkedList<>();
            linkedListBlock.add(block);
            return block;
        } else {
            return block;
        }
    }

    private Block() {
        this.data = "初始化区块";
        this.hash = getSHA256Str(this.data);
    }

    private Block(Block block,String data){
        this.data = data;
        this.hash = getSHA256Str(data);
        this.perHash = block.hash;
    }

    public boolean addBlock(String data,String key){
        if(block != null){
            Block oldBlock = block;
            block = new Block(block,data);
            if(key.equals(oldBlock.hash) || key.equals("zhuran")) {
                linkedListBlock.add(block);
                return true;
            }
        }
        return false;
    }

    public void say(){
        for (Block aLinkedListBlock : linkedListBlock) {
            System.out.println(aLinkedListBlock.perHash);
            System.out.println(aLinkedListBlock.data);
            System.out.println(aLinkedListBlock.hash);
            System.out.println("----------------------------");
        }
    }

    private static String getSHA256Str(String str){
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }
}
