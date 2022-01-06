package club.kwcoder.mapreduce.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
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
        FileInputFormat.setInputPaths(job, new Path("src/main/resources/input"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/output"));

        // 7、提交 job
        /*
        waitForCompletion : 该方法内部调用了 submit() 方法
        boolean verbose : 是否打印进度
         */
        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : 1);
    }

}
