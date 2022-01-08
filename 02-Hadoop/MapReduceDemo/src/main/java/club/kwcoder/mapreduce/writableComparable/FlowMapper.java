package club.kwcoder.mapreduce.writableComparable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper extends Mapper<LongWritable, Text, FlowBean, Text> {

    private final FlowBean outKey = new FlowBean();

    private final Text outValue = new Text();

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, FlowBean, Text>.Context context) throws IOException, InterruptedException {
        // 获取一行 & 切割
        String[] split = value.toString().split("\t");

        outValue.set(split[0]);
        outKey
                .setUpFlow(Long.parseLong(split[1]))
                .setDownFlow(Long.parseLong(split[2]))
                .setSumFlow();

        context.write(outKey, outValue);
    }
}
