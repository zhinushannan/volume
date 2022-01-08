# Map Reduce

## 一、MapReduce概述
### 1.1 MapReduce 定义
MapReduce 是一个分布式运算程序的编程框架，是用户开发“基于 Hadoop 的数据分析应用”的核心框架。
MapReduce 核心功能是将用户编写的业务逻辑代码和自带默认组件整合成一个完整的分布式运算程序，并发运行在一个 Hadoop 集群上。
### 1.2 MapReduce 优缺点
#### 1.2.1 优点
1）MapReduce 易于编程   
它简单的实现一些接口，就可以完成一个分布式程序，这个分布式程序可以分布到大量廉价的 PC 机器上运行。也就是说你写一个分布式程序，跟写一个简单的串行程序是一模一样的。就是因为这个特点使得 MapReduce 编程变得非常流行。
2）良好的扩展性   
当你的计算资源不能得到满足的时候，你可以通过简单的增加机器来扩展它的计算能力。   
3）高容错性   
MapReduce 设计的初衷就是使程序能够部署在廉价的 PC 机器上，这就要求它具有很高的容错性。比如其中一台机器挂了，它可以把上面的计算任务转移到另外一个节点上运行，不至于这个任务运行失败，而且这个过程不需要人工参与，而完全是由 Hadoop 内部完成的。   
4）适合 PB 级以上海量数据的离线处理   
可以实现上千台服务器集群并发工作，提供数据处理能力。   
#### 1.2.2 缺点
1）不擅长实时计算   
MapReduce 无法像 MySQL 一样，在毫秒或者秒级内返回结果。   
2）不擅长流式计算   
流式计算的输入数据是动态的，而 MapReduce 的输入数据集是静态的，不能动态变化。   
这是因为 MapReduce 自身的设计特点决定了数据源必须是静态的。   
3）不擅长 DAG（有向无环图）计算   
多个应用程序存在依赖关系，后一个应用程序的输入为前一个的输出。在这种情况下，MapReduce 并不是不能做，而是使用后，每个 MapReduce 作业的输出结果都会写入到磁盘，会造成大量的磁盘 IO，导致性能非常的低下。   
### 1.3 MapReduce 核心思想
![MapReduce核心思想.png](img/036-MapReduce核心思想.png)   
（1）分布式的运算程序往往需要分成至少 2 个阶段。   
（2）第一个阶段的 MapTask 并发实例，完全并行运行，互不相干。   
（3）第二个阶段的 ReduceTask 并发实例互不相干，但是他们的数据依赖于上一个阶段的所有 MapTask 并发实例的输出。   
（4）MapReduce 编程模型只能包含一个 Map 阶段和一个 Reduce 阶段，如果用户的业务逻辑非常复杂，那就只能多个 MapReduce 程序，串行运行。   
总结：分析 WordCount 数据流走向深入理解 MapReduce 核心思想。   
### 1.4 MapReduce 进程
一个完整的 MapReduce 程序在分布式运行时有三类实例进程：   
（1）MrAppMaster：负责整个程序的过程调度及状态协调。   
（2）MapTask：负责 Map 阶段的整个数据处理流程。   
（3）ReduceTask：负责 Reduce 阶段的整个数据处理流程。   
### 1.5 官方 WordCount 源码
采用反编译工具反编译源码，发现 WordCount 案例有 Map 类、Reduce 类和驱动类。且数据的类型是 Hadoop 自身封装的序列化类型。
### 1.6 常用数据序列化类型

| Java类型 | Hadoop Writable类型 |
| :---: | :---: |
| Boolean | BooleanWritable |
| Byte | ByteWritable |
| Int | IntWritable |
| Float | FloatWritable |
| Long | LongWritable |
| Double | DoubleWritable |
| String | Text |
| Map | MapWritable |
| Array | ArrayWritable |
| Null | NullWritable |

### 1.7 MapReduce 编程规范
用户编写的程序分成三个部分：Mapper、Reducer 和 Driver。
#### 1．Mapper阶段
（1）用户自定义的Mapper要继承自己的父类   
（2）Mapper的输入数据是KV对的形式（KV的类型可自定义）    
（3）Mapper中的业务逻辑写在map()方法中   
（4）Mapper的输出数据是KV对的形式（KV的类型可自定义）    
（5）map()方法（MapTask进程）对每一个<K,V>调用一次
#### 2．Reducer阶段
（1）用户自定义的Reducer要继承自己的父类   
（2）Reducer的输入数据类型对应Mapper的输出数据类型，也是KV   
（3）Reducer的业务逻辑写在reduce()方法中   
（4）ReduceTask进程对每一组相同k的<k,v>组调用一次reduce()方法   
#### 3．Driver阶段
相当于YARN集群的客户端，用于提交我们整个程序到YARN集群，提交的是封装了MapReduce程序相关运行参数的job对象
### 1.8 WordCount 案例实操
#### 1.8.1 本地测试
##### 1）需求：在给定的文本文件中统计输出每一个单词出现的总次数
（1）输入数据（以文件的形式输入）
```text
atguigu atguigu
ss ss
cls cls
jiao
banzhang
xue
hadoop
```
（2）期望输出数据
```text
atguigu 2
banzhang 1
cls 2
hadoop 1
jiao 1
ss 2
xue 1
```
##### 2）需求分析
按照 MapReduce 编程规范，分别编写 Mapper，Reducer，Driver。
##### 3）环境准备
需要把正确的 hadoop.dll 拷贝到 C:\Windows\System32 目录下   
##### 4）代码
[MapReduceDemo](/MapReduceDemo)
##### 5）生成结果
生成的文件及其对应的含义：

| 文件名 | 文件含义 |
| :---: | :---: |
| ._SUCCESS.crc | 校验码 |
| .part-r-00000.crc | 校验码 |
| _SUCCESS | 用来表示执行成功 |
| part-r-00000 | 表示第一块分区的数据 |



## 二、Hadoop 序列化
### 2.1 序列化概述
#### 1）什么是序列化
序列化就是把内存中的对象，转换成字节序列（或其他数据传输协议）以便于存储到磁盘（持久化）和网络传输。   
反序列化就是将收到字节序列（或其他数据传输协议）或者是磁盘的持久化数据，转换成内存中的对象。
#### 2）为什么要序列化
一般来说，“活的”对象只生存在内存里，关机断电就没有了。而且“活的”对象只能由本地的进程使用，不能被发送到网络上的另外一台计算机。 然而序列化可以存储“活的”对象，可以将“活的”对象发送到远程计算机。
#### 3）为什么不用 Java 的序列化
Java 的序列化是一个重量级序列化框架（Serializable），一个对象被序列化后，会附带很多额外的信息（各种校验信息，Header，继承体系等），不便于在网络中高效传输。所以，Hadoop 自己开发了一套序列化机制（Writable）。
#### 4）Hadoop 序列化特点：
（1）紧凑 ：高效使用存储空间。   
（2）快速：读写数据的额外开销小。   
（3）互操作：支持多语言的交互   
### 2.2 自定义 bean 对象实现序列化接口（Writable） 
在企业开发中往往常用的基本序列化类型不能满足所有需求，比如在 Hadoop 框架内部传递一个 bean 对象，那么该对象就需要实现序列化接口。   
具体实现 bean 对象序列化步骤如下 7 步。    
（1）必须实现 Writable 接口   
（2）反序列化时，需要反射调用空参构造函数，所以必须有空参构造   
```java
public FlowBean() {
    super();
}
```
（3）重写序列化方法   
```java
@Override
public void write(DataOutput out) throws IOException {
    out.writeLong(upFlow);
    out.writeLong(downFlow);
    out.writeLong(sumFlow);
}
```
（4）重写反序列化方法
```java
@Override
public void readFields(DataInput in) throws IOException {
    upFlow = in.readLong();
    downFlow = in.readLong();
    sumFlow = in.readLong();
}
```
（5）注意反序列化的顺序和序列化的顺序完全一致   
（6）要想把结果显示在文件中，需要重写 toString()，可用"\t"分开，方便后续用。   
（7）如果需要将自定义的 bean 放在 key 中传输，则还需要实现 Comparable 接口，因为MapReduce 框中的 Shuffle 过程要求对 key 必须能排序。详见后面排序案例。   
```java
@Override
public int compareTo(FlowBean o) {
    // 倒序排列，从大到小
    return this.sumFlow > o.getSumFlow() ? -1 : 1;
}
```
### 2.3 序列化案例实操
#### 1）需求
统计每一个手机号耗费的总上行流量、总下行流量、总流量   
（1）数据
```text
1	13736230513	192.196.100.1	www.atguigu.com	2481	24681	200
2	13846544121	192.196.100.2			264	0	200
3 	13956435636	192.196.100.3			132	1512	200
4 	13966251146	192.168.100.1			240	0	404
5 	18271575951	192.168.100.2	www.atguigu.com	1527	2106	200
6 	84188413	192.168.100.3	www.atguigu.com	4116	1432	200
7 	13590439668	192.168.100.4			1116	954	200
8 	15910133277	192.168.100.5	www.hao123.com	3156	2936	200
9 	13729199489	192.168.100.6			240	0	200
10 	13630577991	192.168.100.7	www.shouhu.com	6960	690	200
11 	15043685818	192.168.100.8	www.baidu.com	3659	3538	200
12 	15959002129	192.168.100.9	www.atguigu.com	1938	180	500
13 	13560439638	192.168.100.10			918	4938	200
14 	13470253144	192.168.100.11			180	180	200
15 	13682846555	192.168.100.12	www.qq.com	1938	2910	200
16 	13992314666	192.168.100.13	www.gaga.com	3008	3720	200
17 	13509468723	192.168.100.14	www.qinghua.com	7335	110349	404
18 	18390173782	192.168.100.15	www.sogou.com	9531	2412	200
19 	13975057813	192.168.100.16	www.baidu.com	11058	48243	200
20 	13768778790	192.168.100.17			120	120	200
21 	13568436656	192.168.100.18	www.alibaba.com	2481	24681	200
22 	13568436656	192.168.100.19			1116	954	200
```
（2）格式
```text
1	13736230513	192.196.100.1	www.atguigu.com	2481	24681	200
id  手机号码      网络IP          域名             上行流量 下行流量  网络状态码
```
#### 2）实操
[代码（writable）](/MapReduceDemo/src/main/java/club/kwcoder/mapreduce/writable)

## 三、MapReduce 框架原理
![MapReduce框架原理.png](037-MapReduce框架原理.png)
### 3.1 InputFormat 数据输入
#### 3.1.1 切片与 MapTask 并行度决定机制
##### 1）问题引出
MapTask 的并行度决定 Map 阶段的任务处理并发度，进而影响到整个 Job 的处理速度。
思考：1G 的数据，启动 8 个 MapTask，可以提高集群的并发处理能力。那么 1K 的数据，也启动 8 个 MapTask，会提高集群性能吗？MapTask 并行任务是否越多越好呢？哪些因素影响了 MapTask 并行度？
##### 2）MapTask 并行度决定机制
数据块：Block 是 HDFS 物理上把数据分成一块一块。数据块是 HDFS 存储数据单位。
数据切片：数据切片只是在逻辑上对输入进行分片，并不会在磁盘上将其切分成片进行存储。数据切片是 MapReduce 程序计算输入数据的单位，一个切片会对应启动一个 MapTask。   
![数据切片与MapTask并行度决定机制.png](038-数据切片与MapTask并行度决定机制.png)
#### 3.1.2 Job 提交流程源码和切片源码详解
##### 1）Job 提交流程源码详解
```java
waitForCompletion();

submit();

// 1建立连接
connect();
// 1）创建提交Job的代理
new Cluster(getConfiguration());
// （1）判断是本地运行环境还是yarn集群运行环境
initialize(jobTrackAddr, conf);

// 2 提交job
submitter.submitJobInternal(Job.this, cluster)

// 1）创建给集群提交数据的Stag路径
Path jobStagingArea = JobSubmissionFiles.getStagingDir(cluster, conf);

// 2）获取jobid ，并创建Job路径
JobID jobId = submitClient.getNewJobID();

// 3）拷贝jar包到集群
copyAndConfigureFiles(job, submitJobDir);
rUploader.uploadFiles(job, jobSubmitDir);

// 4）计算切片，生成切片规划文件
writeSplits(job, submitJobDir);
maps = writeNewSplits(job, jobSubmitDir);
input.getSplits(job);

// 5）向Stag路径写XML配置文件
writeConf(conf, submitJobFile);
conf.writeXml(out);

// 6）提交Job,返回提交状态
status = submitClient.submitJob(jobId, submitJobDir.toString(), job.getCredentials());
```
![Job提交流程源码解析.png](039-Job提交流程源码解析.png)
##### 2）FileInputFormat 切片源码解析（input.getSplits(job)）
（1）程序先找到你数据存储的目录。    
（2）开始遍历处理（规划切片）目录下的每一个文件   
（3）遍历第一个文件ss.txt   
➢ a）获取文件大小fs.sizeOf(ss.txt)    
➢ b）计算切片大小   
`computeSplitSize(Math.max(minSize,Math.min(maxSize,blocksize)))=blocksize=128M`   
➢ c）默认情况下，切片大小=blocksize   
➢ d）开始切，形成第1个切片：ss.txt—0:128M 第2个切片ss.txt—128:256M 第3个切片ss.txt—256M:300M（每次切片时，都要判断切完剩下的部分是否大于块的1.1倍，不大于1.1倍就划分一块切片）   
➢ e）将切片信息写到一个切片规划文件中   
➢ f）整个切片的核心过程在getSplit()方法中完成   
➢ g）InputSplit只记录了切片的元数据信息，比如起始位置、长度以及所在的节点列表等。   
（4）提交切片规划文件到YARN上，YARN上的MrAppMaster就可以根据切片规划文件计算开启MapTask个数。
#### 3.1.3 FileInputFormat 切片机制
##### 1）FileInputFormat切片机制
1、切片机制   
➢ （1）简单地按照文件的内容长度进行切片    
➢ （2）切片大小，默认等于Block大小   
➢ （3）切片时不考虑数据集整体，而是逐个针对每一个文件单独切片   
2、案例分析    
➢ （1）输入数据有两个文件：   
```text
file1.txt 320M
file2.txt 10M
```
➢ （2）经过FileInputFormat的切片机制   
运算后，形成的切片信息如下：   
```text
file1.txt.split1-- 0~128
file1.txt.split2-- 128~256
file1.txt.split3-- 256~320
file2.txt.split1-- 0~10M
```
##### 2）FileInputFormat切片大小的参数配置
（1）源码中计算切片大小的公式
```text
Math.max(minSize, Math.min(maxSize, blockSize));
mapreduce.input.fileinputformat.split.minsize=1 默认值为1
mapreduce.input.fileinputformat.split.maxsize= Long.MAXValue 默认值Long.MAXValue
```
因此，默认情况下，切片大小=blocksize。   
（2）切片大小设置   
```text
maxsize（切片最大值）：参数如果调得比blockSize小，则会让切片变小，而且就等于配置的这个参数的值。   
minsize（切片最小值）：参数调的比blockSize大，则可以让切片变得比blockSize还大。  
```
（3）获取切片信息API   
```text
// 获取切片的文件名称
String name = inputSplit.getPath().getName();
// 根据文件类型获取切片信息
FileSplit inputSplit = (FileSplit) context.getInputSplit();
```
#### 3.1.4 TextInputFormat
##### 1）FileInputFormat 实现类
思考：在运行 MapReduce 程序时，输入的文件格式包括：基于行的日志文件、二进制格式文件、数据库表等。那么，针对不同的数据类型，MapReduce 是如何读取这些数据的呢？   
FileInputFormat 常见的接口实现类包括：TextInputFormat、KeyValueTextInputFormat、NLineInputFormat、CombineTextInputFormat 和自定义 InputFormat 等。   
##### 2）TextInputFormat
TextInputFormat 是默认的 FileInputFormat 实现类。按行读取每条记录。键是存储该行在整个文件中的起始字节偏移量， LongWritable 类型。值是这行的内容，不包括任何行终止符（换行符和回车符），Text 类型。   
以下是一个示例，比如，一个分片包含了如下 4 条文本记录。   
```text
Rich learning form
Intelligent learning engine
Learning more convenient
From the real demand for more close to the enterprise
```
每条记录表示为以下键/值对：
```text
(0,Rich learning form)
(20,Intelligent learning engine)
(49,Learning more convenient)
(74,From the real demand for more close to the enterprise)
```
#### 3.1.5 CombineTextInputFormat 切片机制
框架默认的 TextInputFormat 切片机制是对任务按文件规划切片，不管文件多小，都会是一个单独的切片，都会交给一个 MapTask，这样如果有大量小文件，就会产生大量的MapTask，处理效率极其低下。
##### 1）应用场景：
CombineTextInputFormat 用于小文件过多的场景，它可以将多个小文件从逻辑上规划到一个切片中，这样，多个小文件就可以交给一个 MapTask 处理。 
##### 2）虚拟存储切片最大值设置
`CombineTextInputFormat.setMaxInputSplitSize(job, 4194304);// 4m`   
注意：虚拟存储切片最大值设置最好根据实际的小文件大小情况来设置具体的值。
##### 3）切片机制
生成切片过程包括：虚拟存储过程和切片过程二部分。     
![CombineTextInputFormat切片机制.png](040-CombineTextInputFormat切片机制.png)     
（1）虚拟存储过程：
将输入目录下所有文件大小，依次和设置的 setMaxInputSplitSize 值比较，如果不大于设置的最大值，逻辑上划分一个块。如果输入文件大于设置的最大值且大于两倍，那么以最大值切割一块；当剩余数据大小超过设置的最大值且不大于最大值 2 倍，此时将文件均分成 2 个虚拟存储块（防止出现太小切片）。   
例如 setMaxInputSplitSize 值为 4M，输入文件大小为 8.02M，则先逻辑上分成一个4M。剩余的大小为 4.02M，如果按照 4M 逻辑划分，就会出现 0.02M 的小的虚拟存储文件，所以将剩余的 4.02M 文件切分成（2.01M 和 2.01M）两个文件。    
（2）切片过程：   
➢ （a）判断虚拟存储的文件大小是否大于 setMaxInputSplitSize 值，大于等于则单独形成一个切片。   
➢ （b）如果不大于则跟下一个虚拟存储文件进行合并，共同形成一个切片。   
➢ （c）测试举例：有 4 个小文件大小分别为 1.7M、5.1M、3.4M 以及 6.8M 这四个小文件，则虚拟存储之后形成 6 个文件块，大小分别为：   
`1.7M，（2.55M、2.55M），3.4M 以及（3.4M、3.4M）`   
最终会形成 3 个切片，大小分别为：    
`（1.7+2.55）M，（2.55+3.4）M，（3.4+3.4）M`
#### 3.1.6 CombineTextInputFormat 案例实操
##### 1）需求
将输入的大量小文件合并成一个切片统一处理。    
（1）输入数据   
准备四个小文件   
（2）期望   
期望一个切片处理 4 个文件   
##### 2）实现过程
[代码 combineTextInputformat](/MapReduceDemo/src/main/java/club/kwcoder/mapreduce/combineTextInputformat)

### 3.2 MapReduce 工作流程
![MapReduce详细工作流程（一）.png](041-MapReduce详细工作流程（一）.png)   
![MapReduce详细工作流程（二）.png](042-MapReduce详细工作流程（二）.png)   
上面的流程是整个 MapReduce 最全工作流程，但是 Shuffle 过程只是从第 7 步开始到第 16 步结束，具体 Shuffle 过程详解，如下：   
（1）MapTask 收集我们的 map()方法输出的 kv 对，放到内存缓冲区中   
（2）从内存缓冲区不断溢出本地磁盘文件，可能会溢出多个文件   
（3）多个溢出文件会被合并成大的溢出文件   
（4）在溢出过程及合并的过程中，都要调用 Partitioner 进行分区和针对 key 进行排序   
（5）ReduceTask 根据自己的分区号，去各个 MapTask 机器上取相应的结果分区数据   
（6）ReduceTask 会抓取到同一个分区的来自不同 MapTask 的结果文件，ReduceTask 会将这些文件再进行合并（归并排序）   
（7）合并成大文件后，Shuffle 的过程也就结束了，后面进入 ReduceTask 的逻辑运算过程（从文件中取出一个一个的键值对 Group，调用用户自定义的 reduce()方法）   
注意：    
（1）Shuffle 中的缓冲区大小会影响到 MapReduce 程序的执行效率，原则上说，缓冲区越大，磁盘 io 的次数越少，执行速度就越快。   
（2）缓冲区的大小可以通过参数调整，参数：mapreduce.task.io.sort.mb 默认 100M。   

### 3.3 Shuffle 机制
#### 3.3.1 Shuffle 机制
Map 方法之后，Reduce 方法之前的数据处理过程称之为 Shuffle。   
![Shuffle机制.png](043-Shuffle机制.png)
#### 3.3.2 Partition 分区
##### 1、问题引出   
要求将统计结果按照条件输出到不同文件中（分区）。比如：将统计结果按照手机归属地不同省份输出到不同文件中（分区） 
##### 2、默认Partitioner分区   
```java
public class HashPartitioner<K, V> extends Partitioner<K, V> {
    public int getPartition(K key, V value, int numReduceTasks) {
        return (key.hashCode() & Integer.MAX_VALUE) % numReduceTasks; 
    } 
}
```
默认分区是根据key的hashCode对ReduceTasks个数取模得到的。用户没法控制哪个key存储到哪个分区。
##### 3、自定义Partitioner步骤
（1）自定义类继承Partitioner，重写getPartition()方法   
```java
public class CustomPartitioner extends Partitioner<Text, FlowBean> {
    @Override
    public int getPartition(Text key, FlowBean value, int numPartitions) {
        // 控制分区代码逻辑
        // … …
        return partition; 
    } 
}
```
（2）在Job驱动中，设置自定义Partitioner   
`job.setPartitionerClass(CustomPartitioner.class);`   
（3）自定义Partition后，要根据自定义Partitioner的逻辑设置相应数量的ReduceTask   
`job.setNumReduceTasks(5);`
##### 4、分区总结
（1）如果ReduceTask的数量> getPartition的结果数，则会多产生几个空的输出文件part-r-000xx；   
（2）如果1<ReduceTask的数量<getPartition的结果数，则有一部分分区数据无处安放，会Exception；    
（3）如果ReduceTask的数量=1，则不管MapTask端输出多少个分区文件，最终结果都交给这一个ReduceTask，最终也就只会产生一个结果文件 part-r-00000； 
##### 5、案例分析
例如：假设自定义分区数为5，则   
（1）`job.setNumReduceTasks(1);` 会正常运行，只不过会产生一个输出文件    
（2）`job.setNumReduceTasks(2);` 会报错   
（3）`job.setNumReduceTasks(6);` 大于5，程序会正常运行，会产生空文件     

#### 3.3.3 Partition 分区案例实操
（1）需求   
将统计结果按照手机归属地不同省份输出到不同文件中（分区）   
（2）期望输出数据   
手机号 136、137、138、139 开头都分别放到一个独立的 4 个文件中，其他开头的放到一个文件中。   
（3）项目代码   
[代码 partitioner](/MapReduceDemo/src/main/java/club/kwcoder/mapreduce/partitioner)
