package club.kwcoder.mapreduce.reduceJoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

public class TableMapper extends Mapper<LongWritable, Text, Text, TableBean> {

    private String fileName;

    private Text outKey = new Text();

    private TableBean outValue = new TableBean();

    /**
     * 一个切片一个 map， 一个map一个setup
     * 没有设置分区的话，一个小文件一个map
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Mapper<LongWritable, Text, Text, TableBean>.Context context) throws IOException, InterruptedException {

        // 初始化 order pd
        FileSplit split = (FileSplit) context.getInputSplit();

        fileName = split.getPath().getName();

    }


    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, TableBean>.Context context) throws IOException, InterruptedException {

        // 获取一行
        String line = value.toString();
        String[] split = line.split("\t");

        // 判断时那个文件
        if (fileName.contains("order")) {
            // 处理订单表

            // 封装对应的 K、V
            outKey.set(split[1]);

            outValue
                    .setId(split[0])
                    .setPid(split[1])
                    .setAmount(Integer.parseInt(split[2]))
                    .setFlag("order")
                    // 因为需要 Writable 序列化，所以不能为 null
                    .setPname("");

        } else {
            // 处理商品表


            outKey.set(split[0]);

            outValue
                    .setId("")
                    .setPid(split[0])
                    .setPname(split[1])
                    .setAmount(0)
                    .setFlag("pd");

        }

        context.write(outKey, outValue);

    }
}
