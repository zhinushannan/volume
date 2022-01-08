package club.kwcoder.mapreduce.partitioner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper extends Mapper<LongWritable, Text, Text, FlowBean> {

    private final Text outKey = new Text();

    private final FlowBean outValue = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, FlowBean>.Context context) throws IOException, InterruptedException {
        // 1	13736230513	192.196.100.1	www.atguigu.com	2481	24681	200
        // 序号   手机号     IP              域名              上行流量 下行流量 网络状态码

        String[] line = value.toString().split("\t");

        String phone = line[1];
        String upFlow = line[line.length - 3];
        String downFlow = line[line.length - 2];

        outValue
                .setUpFlow(Long.parseLong(upFlow))
                .setDownFlow(Long.parseLong(downFlow))
                .setSumFlow();

        outKey.set(phone);
        context.write(outKey, outValue);
    }
}
