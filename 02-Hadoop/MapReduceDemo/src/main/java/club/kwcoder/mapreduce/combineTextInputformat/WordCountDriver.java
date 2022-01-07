package club.kwcoder.mapreduce.combineTextInputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCountDriver {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        // 1、获取 job
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 2、设置 jar 包路径
        job.setJarByClass(WordCountDriver.class);

        // 3、关联 mapper 和 reducer
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        // 4、设置 map 输出的 K、V 类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 5、设置最终输出的 K、V 类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);



        // 6、设置输入路径和输出路径
        // 设置 inputFormat ，如果不设置，默认使用 TextInputFormat.class
        job.setInputFormatClass(CombineTextInputFormat.class);
        CombineTextInputFormat.setMaxInputSplitSize(job, 20971520);

        FileInputFormat.setInputPaths(job, new Path("02-Hadoop/MapReduceDemo/src/main/resources/03-input-combine_text_inputformat"));
        FileOutputFormat.setOutputPath(job, new Path("02-Hadoop/MapReduceDemo/src/main/resources/03-output-combine_text_inputformat"));

        // 7、提交 job
        /*
        waitForCompletion : 该方法内部调用了 submit() 方法
        boolean verbose : 是否打印进度
         */
        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : 1);
    }

}
