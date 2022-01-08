package club.kwcoder.mapreduce.partitioner;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class ProvincePartitioner extends Partitioner<Text, FlowBean> {


    @Override
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
        // text 是手机号

        String phone = text.toString();

        String prePhone = phone.substring(0, 3);

        int partition;
        switch (prePhone) {
            case "136":
                partition = 0;
                break;
            case "137":
                partition = 1;
                break;
            case "138":
                partition = 2;
                break;
            case "139":
                partition = 3;
                break;
            default:
                partition = 4;
                break;
        }

        return partition;
    }
}
