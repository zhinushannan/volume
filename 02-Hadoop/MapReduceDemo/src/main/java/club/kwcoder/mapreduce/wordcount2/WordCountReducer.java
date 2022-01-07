package club.kwcoder.mapreduce.wordcount2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 *
 * KEYIN : map阶段输入的key的类型
 * VALUEIN : map阶段输入value的类型
 * KEYOUT : map阶段输出的key的类型
 * VALUEOUT : map阶段输出的value
 *
 * ========================================
 *
 * map的输出是reduce的输入，因此在泛型方面是和map相反的
 *
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private final IntWritable outValue = new IntWritable();

    // 数据："atguigu", (1, 1)
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        int sum = 0;

        // 1、累加
        for (IntWritable value : values) {
            sum += value.get();
        }

        this.outValue.set(sum);
        // 2、写出
        context.write(key, this.outValue);

    }
}
