package club.kwcoder.mapreduce.writable;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 1、定义类实现 Writable 接口
 * 2、重写序列化和反序列化方法
 * 3、重写空参构造
 * 4、toString方法
 */
public class FlowBean implements Writable {

    private Long upFlow;
    private Long downFlow;
    private Long sumFlow;

    public FlowBean() {}

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.upFlow = in.readLong();
        this.downFlow = in.readLong();
        this.sumFlow = in.readLong();
    }


    public Long getUpFlow() {
        return upFlow;
    }

    public FlowBean setUpFlow(Long upFlow) {
        this.upFlow = upFlow;
        return this;
    }

    public Long getDownFlow() {
        return downFlow;
    }

    public FlowBean setDownFlow(Long downFlow) {
        this.downFlow = downFlow;
        return this;
    }

    public Long getSumFlow() {
        return sumFlow;
    }

    public FlowBean setSumFlow(Long sumFlow) {
        this.sumFlow = sumFlow;
        return this;
    }

    public FlowBean setSumFlow() {
        this.sumFlow = this.upFlow + this.downFlow;
        return this;
    }

    @Override
    public String toString() {
        return upFlow + "\t" + downFlow + "\t" + sumFlow;
    }

}
