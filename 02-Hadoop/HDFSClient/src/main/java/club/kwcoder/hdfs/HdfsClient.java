package club.kwcoder.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

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
        Path[] srcs / Path src: 数据源目录（本地路径）
        Path dst: 目标路径（hdfs路径）
         */
        fs.copyFromLocalFile(false, true, new Path("src/main/resources/file/sunwukong.txt"), new Path("/xiyou/huaguoshan"));
    }

    @Test
    public void testGet() throws IOException {
        /*
        boolean delSrc: 下载的源文件是否删除
        Path src: 源文件的路径（hdfs路径）
        Path dst: 目标地址路径（本地路径）
        boolean useRawLocalFileSystem: 是否开启本地文件校验
         */
        fs.copyToLocalFile(false, new Path("/xiyou/huaguoshan/log4j.properties"), new Path("src/main/resources/file/"), true);
    }

    @Test
    public void testRm() throws IOException {
        /*
        Path f: 要删除的路径
        boolean recursive: 是否递归删除
         */

        // 删除文件
        // fs.delete(new Path("/xiyou/huaguoshan/log4j.properties"), false);

        // 删除空目录
        fs.delete(new Path("/xiyou/huaguoshan/s/"), false);

        // 删除非空目录
        // fs.delete(new Path(""), true);
    }

    @Test
    public void testMv() throws IOException {
        /*
        Path src: 原文件路径
        Path dst: 目标文件路径
         */
        fs.rename(new Path("/xiyou/huaguoshan/s/sunwukong.txt"), new Path("/xiyou/huaguoshan/sunwukong.txt"));
    }

    @Test
    public void fileDetail() throws IOException {
        /*
        final Path f: 目录路径
        final boolean recursive: 是否递归
         */
        RemoteIterator<LocatedFileStatus> iterator = fs.listFiles(new Path("/"), true);
        while (iterator.hasNext()) {
            LocatedFileStatus fileStatus = iterator.next();
            System.out.println("=====" + fileStatus.getPath() + "=====");
            System.out.println(fileStatus.getPermission());
            System.out.println(fileStatus.getOwner());
            System.out.println(fileStatus.getGroup());
            System.out.println(fileStatus.getLen());
            System.out.println(fileStatus.getModificationTime());
            System.out.println(fileStatus.getReplication());
            System.out.println(fileStatus.getBlockSize());
            System.out.println(fileStatus.getPath().getName());
            // 获取块信息
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            System.out.println(Arrays.toString(blockLocations));
        }
    }

    /**
     * 判断是文件夹还是文件
     */
    @Test
    public void testFile() throws IOException {
        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : fileStatuses) {
            if (fileStatus.isFile()) {
                System.out.println("文件：" + fileStatus.getPath().getName());
            } else {
                System.out.println("目录：" + fileStatus.getPath().getName());
            }
        }
    }

}
