package club.kwcoder.mapreduce.reduceJoin;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableReducer extends Reducer<Text, TableBean, TableBean, NullWritable> {

    /**
     * id都为 0 的对象统一进入这个方法
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<TableBean> values, Reducer<Text, TableBean, TableBean, NullWritable>.Context context) throws IOException, InterruptedException {
        List<TableBean> orderBeans = new ArrayList<>();

        TableBean pdBean = new TableBean();

        for (TableBean value : values) {
            if ("order".equals(value.getFlag())) {
                // 订单表

                /*
                Hadoop迭代器中使用了对象重用，即迭代时value始终指向一个内存地址（引用值始终不变），
                改变的是引用指向的内存地址中的数据
                为了避免这个情况，需要进行深拷贝
                 */
                TableBean tempTableBean = new TableBean();

                tempTableBean
                        .setId(value.getId())
                        .setPid(value.getPid())
                        .setPname(value.getPname())
                        .setAmount(value.getAmount())
                        .setFlag(value.getFlag());

                orderBeans.add(tempTableBean);
            } else {
                // 商品表

                pdBean
                        .setId(value.getId())
                        .setPid(value.getPid())
                        .setPname(value.getPname())
                        .setAmount(value.getAmount())
                        .setFlag(value.getFlag());

            }
        }


        // 循环遍历 orderBeans ，赋值 pdname
        for (TableBean orderBean : orderBeans) {

            orderBean.setPname(pdBean.getPname());


            context.write(orderBean, NullWritable.get());

        }


    }
}
