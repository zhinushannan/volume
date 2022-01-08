package club.kwcoder.mapreduce.partitioner;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowDriver {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        Job job = Job.getInstance();

        job.setJarByClass(FlowDriver.class);

        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        // 设置 partitioner 和 numReduceTasks
        /*
        实现根据手机号划分文件
         */
        job.setPartitionerClass(ProvincePartitioner.class);
        job.setNumReduceTasks(5);

        FileInputFormat.setInputPaths(job, new Path("02-Hadoop/MapReduceDemo/src/main/resources/02-input-phone_data"));
        FileOutputFormat.setOutputPath(job, new Path("02-Hadoop/MapReduceDemo/src/main/resources/02-output-phone_data"));

        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);

    }

}
