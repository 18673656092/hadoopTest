package blockchain;

import java.io.Serializable;

/**
 * Created by zhuran on 2018/7/17 0017
 */
public class BlockDate implements Serializable {

    private String date;

    private String key;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
