package club.kwcoder.mapreduce.reduceJoin;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TableBean implements Writable {

    // 订单id
    private String id;

    // 商品id
    private String pid;

    // 商品数量
    private int amount;

    // 商品名称
    private String pname;

    // 标记是什么表
    private String flag;

    public TableBean() {
    }

    public String getId() {
        return id;
    }

    public TableBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getPid() {
        return pid;
    }

    public TableBean setPid(String pid) {
        this.pid = pid;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public TableBean setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public String getPname() {
        return pname;
    }

    public TableBean setPname(String pname) {
        this.pname = pname;
        return this;
    }

    public String getFlag() {
        return flag;
    }

    public TableBean setFlag(String flag) {
        this.flag = flag;
        return this;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeUTF(pid);
        out.writeInt(amount);
        out.writeUTF(pname);
        out.writeUTF(flag);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        id = in.readUTF();
        pid = in.readUTF();
        amount = in.readInt();
        pname = in.readUTF();
        flag = in.readUTF();
    }

    @Override
    public String toString() {
        return id + "\t" + pname + '\t' + amount;
    }

}
