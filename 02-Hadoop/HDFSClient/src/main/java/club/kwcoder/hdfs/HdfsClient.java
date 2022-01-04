package club.kwcoder.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
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

    private FileSystem fs;

    @Before
    public void init() throws IOException, InterruptedException, URISyntaxException {
        // 指定系统变量
        System.setProperty("hadoop.home.dir", "F:\\hadoop-3.1.0");

        // 链接的集群NameNode的地址
        URI uri = new URI("hdfs://hadoop102:8020");
        // 创建一个配置文件

        Configuration configuration = new Configuration();
        configuration.set("dfs.replication", "2");

        String user = "root";

        // 1、获取客户端对象
        fs = FileSystem.get(uri, configuration, user);
    }

    @After
    public void close () throws IOException {
        fs.close();
    }

    @Test
    public void testMkDir() throws IOException {
        // 2、执行相关操作
        fs.mkdirs(new Path("/xiyou/huaguoshan1"));
    }

    /**
     * 参数优先级：
     * hdfs-default.xml -> hdfs-site.xml -> resources/hdfs-site.xml -> org.apache.hadoop.conf.Configuration
     * @throws IOException throws IOException
     */
    @Test
    public void testPut() throws IOException {
        /*
        boolean delSrc: 表示是否删除源文件
        boolean overwrite: 表示当目标路径或文件存在时是否覆盖
        Path[] srcs / Path src: 数据源目录
        Path dst: 目标路径
         */
        fs.copyFromLocalFile(false, true, new Path("src/main/resources/file/sunwukong.txt"), new Path("/xiyou/huaguoshan"));
    }

}
