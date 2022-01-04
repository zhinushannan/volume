package club.kwcoder.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 客户端代码常用步骤：
 * 1、获取一个客户端对象
 * 2、执行相关的操作命令
 * 3、关闭资源
 */
public class HdfsClient {

    @Test
    public void testMkDir() throws URISyntaxException, IOException, InterruptedException {
        // 指定系统变量
        System.setProperty("hadoop.home.dir", "F:\\hadoop-3.1.0");

        // 链接的集群NameNode的地址
        URI uri = new URI("hdfs://hadoop102:8020");
        // 创建一个配置文件

        Configuration configuration = new Configuration();

        String user = "root";

        // 1、获取客户端对象
        FileSystem fs = FileSystem.get(uri, configuration, user);

        // 2、执行相关操作
        fs.mkdirs(new Path("/xiyou/huaguoshan"));

        // 3、关闭资源
        fs.close();

    }

}
