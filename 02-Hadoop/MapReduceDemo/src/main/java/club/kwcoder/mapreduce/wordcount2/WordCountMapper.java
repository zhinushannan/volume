package club.kwcoder.mapreduce.wordcount2;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Mapper 有两个包：
 *  org.apache.hadoop.mapred.Mapper
 *  org.apache.hadoop.mapreduce.Mapper
 *
 *  mapred包是 1.x 版本的包
 *  mapreduce包是 2.x 和 3.x 版本的包
 *
 *  1.x版本时，MapReduce负责执行和调度
 *  2.x和3.x版本时，MapReduce只负责执行，YARN负责调度
 *
 * ==========================================================
 *
 * KEYIN : map阶段输入的key的类型
 * VALUEIN : map阶段输入value的类型
 * KEYOUT : map阶段输出的key的类型
 * VALUEOUT : map阶段输出的value
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    /*
    放在此处的原因：
    对于一个文件来说，有多少行就要执行多少次 map 方法
    对于一行来说，有多少个单词就要执行多少次循环写出
    当对象会被程序连续创建时，会严重消耗内存从而性能降低，若对象只被创建一次，则可以避免这个问题
     */
    private final Text outKey = new Text();

    private final IntWritable outValue = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        // 1、获取一行 ------ "atguigu atguigu"
        String line = value.toString();

        // 2、切割 ------ ["atguigu", "atguigu"]
        String[] words = line.split(" ");

        // 3、循环写出 ------ [Text("atguigu"):KEYOUT, IntWritable(1):VALUEOUT], [Text("atguigu"):KEYOUT, IntWritable(1):VALUEOUT]
        for (String word : words) {
            this.outKey.set(word);
            context.write(this.outKey, this.outValue);
        }

    }
}
