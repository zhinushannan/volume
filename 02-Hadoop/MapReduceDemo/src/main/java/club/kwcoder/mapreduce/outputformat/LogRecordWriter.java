package club.kwcoder.mapreduce.outputformat;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

public class LogRecordWriter extends RecordWriter<Text, NullWritable> {

    private FSDataOutputStream atguigu;
    private FSDataOutputStream other;

    public LogRecordWriter(TaskAttemptContext job) {
        try {
            FileSystem fs = FileSystem.get(job.getConfiguration());

            atguigu = fs.create(new Path("02-Hadoop/MapReduceDemo/src/main/resources/04-output-log/atguigu.log"));
            other = fs.create(new Path("02-Hadoop/MapReduceDemo/src/main/resources/04-output-log/other.log"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Text key, NullWritable value) throws IOException, InterruptedException {
        String log = key.toString();
        if (log.contains("atguigu")) {
            atguigu.writeBytes(log + "\n");
        } else {
            other.writeBytes(log + "\n");
        }
    }

    @Override
    public void close(TaskAttemptContext context) {
        IOUtils.closeStream(atguigu);
        IOUtils.closeStream(other);
    }
}
