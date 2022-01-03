# Hadoop 入门
## 一、Hadoop是什么
### 1.1 Hadoop是什么
1）Hadoop是一个由Apache基金会所开发的分布式系统基础架构。   
2）主要解决，海量数据的存储和海量数据的分析计算问题。   
3）广义上来说，Hadoop通常是指一个更广泛的概念——Hadoop生态圈。   
![Hadoop生态圈](img/002-Hadoop生态圈.png)
### 1.2 Hadoop发展历史（了解）
1）Hadoop创始人Doug Cutting，为 了实 现与Google类似的全文搜索功能，他在Lucene框架基础上进行优化升级，查询引擎和索引引擎。   
![Hadoop创始人Doug Cutting](img/003-Hadoop创始人Doug%20Cutting.png)   
2）2001年年底Lucene成为Apache基金会的一个子项目。   
3）对于海量数据的场景，Lucene框架面对与Google同样的困难，存储海量数据困难，检索海量速度慢。   
4）学习和模仿Google解决这些问题的办法 ：微型版Nutch。   
5）可以说Google是Hadoop的思想之源（Google在大数据方面的三篇论文）   
GFS --->HDFS   
Map-Reduce --->MR   
BigTable --->HBase   
6）2003-2004年，Google公开了部分GFS和MapReduce思想的细节，以此为基础Doug Cutting等人用 了2年业余时间实现了DFS和MapReduce机制，使Nutch性能飙升。   
7）2005年Hadoop作为Lucene的子项目Nutch的一部分正式引入Apache基金会。   
8）2006年3月份，Map-Reduce和Nutch Distributed File System （NDFS）分别被纳入到 Hadoop 项目中，Hadoop就此正式诞生，标志着大数据时代来临。   
9）名字来源于Doug Cutting儿子的玩具大象   
![Hadoop的LOGO](img/004-Hadoop的LOGO.png)
### 1.3 Hadoop三大发行版本（了解）
Hadoop 三大发行版本：Apache、Cloudera、Hortonworks。   
Apache 版本最原始（最基础）的版本，对于入门学习最好。（2006年）   
Cloudera 内部集成了很多大数据框架，对应产品 CDH。（2008年）   
Hortonworks 文档较好，对应产品 HDP。（2011年）   
Hortonworks 现在已经被 Cloudera 公司收购，推出新的品牌 CDP。
#### 1）Apache Hadoop
官网地址：[http://hadoop.apache.org](http://hadoop.apache.org)   
下载地址：[https://hadoop.apache.org/releases.html](https://hadoop.apache.org/releases.html)
#### 2）Cloudera Hadoop
官网地址：[https://www.cloudera.com/downloads/cdh](https://www.cloudera.com/downloads/cdh)   
下载地址：[https://docs.cloudera.com/documentation/enterprise/6/releasenotes/topics/rg_cdh_6_download.html](https://docs.cloudera.com/documentation/enterprise/6/releasenotes/topics/rg_cdh_6_download.html)   
（1）2008 年成立的 Cloudera 是最早将 Hadoop 商用的公司，为合作伙伴提供 Hadoop 的商用解决方案，主要是包括支持、咨询服务、培训。   
（2）2009 年 Hadoop 的创始人 Doug Cutting 也加盟 Cloudera 公司。Cloudera 产品主要为 CDH，Cloudera Manager，Cloudera Support   
（3）CDH 是 Cloudera 的 Hadoop 发行版，完全开源，比 Apache Hadoop 在兼容性，安全性，稳定性上有所增强。Cloudera 的标价为每年每个节点 10000 美元。   
（4）Cloudera Manager 是集群的软件分发及管理监控平台，可以在几个小时内部署好一个 Hadoop 集群，并对集群的节点及服务进行实时监控。
#### 3）Hortonworks Hadoop
官网地址：[https://hortonworks.com/products/data-center/hdp/](https://hortonworks.com/products/data-center/hdp/)   
下载地址：[https://hortonworks.com/downloads/#data-platform](https://hortonworks.com/downloads/#data-platform)   
（1）2011 年成立的 Hortonworks 是雅虎与硅谷风投公司 Benchmark Capital 合资组建。   
（2）公司成立之初就吸纳了大约 25 名至 30 名专门研究 Hadoop 的雅虎工程师，上述工程师均在 2005 年开始协助雅虎开发 Hadoop，贡献了 Hadoop80%的代码。   
（3）Hortonworks 的主打产品是 Hortonworks Data Platform（HDP），也同样是 100%开源的产品，HDP 除常见的项目外还包括了 Ambari，一款开源的安装和管理系统。   
（4）2018 年 Hortonworks 目前已经被 Cloudera 公司收购。
### 1.4 Hadoop 优势（4 高）
1）高可靠性：Hadoop底层维护多个数据副本，所以即使Hadoop某个计算元
素或存储出现故障，也不会导致数据的丢失。   
2）高扩展性：在集群间分配任务数据，可方便的扩展数以千计的节点。   
3）高效性：在MapReduce的思想下，Hadoop是并行工作的，以加快任务处
理速度。   
4）高容错性：能够自动将失败的任务重新分配。
### 1.5 Hadoop组成（面试重点）
在 Hadoop1.x 时代，Hadoop中的MapReduce同时处理业务逻辑运算和资源的调度，耦合性较大。   
在Hadoop2.x时代，增加 Yarn。Yarn只负责资源的调度，MapReduce只负责运算。   
Hadoop3.x在组成上没有变化。   
![Hadoop的组成变化](img/005-Hadoop的组成变化.png)
#### 1.5.1 HDFS架构概述
Hadoop Distributed File System，简称 HDFS，是一个分布式文件系统。   
1）NameNode（nn）：存储文件的元数据，如文件名，文件目录结构，文件属性（生成时间、副本数、文件权限），以及每个文件的块列表和块所在的DataNode等。   
2）DataNode(dn)：在本地文件系统存储文件块数据，以及块数据的校验和。   
3）Secondary NameNode(2nn)：每隔一段时间对NameNode元数据备份。
#### 1.5.2 YARN架构概述
Yet Another Resource Negotiator 简称 YARN ，另一种资源协调者，是 Hadoop 的资源管理器。
1）ResourceManager（RM）：整个集群资源（内存、CPU等）的老大   
3）ApplicationMaster（AM）：单个任务运行的老大   
2）NodeManager（N M）：单个节点服务器资源老大   
4）Container：容器，相当一台独立的服务器，里面封装了任务运行所需要的资源，如内存、CPU、磁盘、网络等。   
![YARN架构概述](img/006-YARN架构概述.png)   
说明1：客户端可以有多个   
说明2：集群上可以运行多个ApplicationMaster   
说明3：每个NodeManager上可以有多个Container
#### 1.5.3 MapReduce 架构概述
MapReduce 将计算过程分为两个阶段：Map 和 Reduce   
1）Map 阶段并行处理输入数据   
2）Reduce 阶段对 Map 结果进行汇总
![MapReduce架构概述](img/007-MapReduce架构概述.png)
#### 1.5.4 HDFS、YARN、MapReduce三者关系
![HDFS、YARN、MapReduce三者关系](img/008-HDFS、YARN、MapReduce三者关系.png)
### 1.6 大数据技术生态体系
![大数据技术生态体系](img/009-大数据技术生态体系.png)   
图中涉及的技术名词解释如下：
1）Sqoop：Sqoop 是一款开源的工具，主要用于在 Hadoop、Hive 与传统的数据库（MySQL）间进行数据的传递，可以将一个关系型数据库（例如 ：MySQL，Oracle 等）中的数据导进到 Hadoop 的 HDFS 中，也可以将 HDFS 的数据导进到关系型数据库中。   
2）Flume：Flume 是一个高可用的，高可靠的，分布式的海量日志采集、聚合和传输的系统，Flume 支持在日志系统中定制各类数据发送方，用于收集数据；   
3）Kafka：Kafka 是一种高吞吐量的分布式发布订阅消息系统；   
4）Spark：Spark 是当前最流行的开源大数据内存计算框架。可以基于 Hadoop 上存储的大数据进行计算。   
5）Flink：Flink 是当前最流行的开源大数据内存计算框架。用于实时计算的场景较多。   
6）Oozie：Oozie 是一个管理 Hadoop 作业（job）的工作流程调度管理系统。   
7）Hbase：HBase 是一个分布式的、面向列的开源数据库。HBase 不同于一般的关系数据库，它是一个适合于非结构化数据存储的数据库。   
8）Hive：Hive 是基于 Hadoop 的一个数据仓库工具，可以将结构化的数据文件映射为一张数据库表，并提供简单的 SQL 查询功能，可以将 SQL 语句转换为 MapReduce 任务进行运行。其优点是学习成本低，可以通过类 SQL 语句快速实现简单的 MapReduce 统计，不必开发专门的 MapReduce 应用，十分适合数据仓库的统计分析。   
9）ZooKeeper：它是一个针对大型分布式系统的可靠协调系统，提供的功能包括：配置维护、名字服务、分布式同步、组服务等。
### 1.7 推荐系统框架图
![推荐系统框架图](img/010-推荐系统框架图.png)
## 二、Hadoop 运行环境搭建（开发重点）
### 2.1 模板虚拟机环境准备

#### 1）hadoop100 虚拟机配置要求（本文 Linux 系统全部以 CentOS-7.5-x86-1804 为例）
（1）使用 yum 安装需要虚拟机可以正常上网，yum 安装前可以先测试下虚拟机联网情况
```shell
[root@hadoop100 ~]# ping www.baidu.com
PING www.baidu.com (14.215.177.39) 56(84) bytes of data.
64 bytes from 14.215.177.39 (14.215.177.39): icmp_seq=1
ttl=128 time=8.60 ms
64 bytes from 14.215.177.39 (14.215.177.39): icmp_seq=2
ttl=128 time=7.72 ms
```
（2）安装 epel-release   
注：Extra Packages for Enterprise Linux 是为“红帽系”的操作系统提供额外的软件包，适用于 RHEL、CentOS 和 Scientific Linux。相当于是一个软件仓库，大多数 rpm 包在官方repository 中是找不到的）
```shell
[root@hadoop100 ~]# yum install -y epel-release
```
（3）注意：如果 Linux 安装的是最小系统版，还需要安装如下工具；如果安装的是 Linux桌面标准版，不需要执行如下操作   
➢ net-tool：工具包集合，包含 ifconfig 等命令
```shell
[root@hadoop100 ~]# yum install -y net-tools
```
➢ vim：编辑器   
```shell
[root@hadoop100 ~]# yum install -y vim
```
#### 2）关闭防火墙，关闭防火墙开机自启
```shell
[root@hadoop100 ~]# systemctl stop firewalld
[root@hadoop100 ~]# systemctl disable firewalld.service
```
注意：在企业开发时，通常单个服务器的防火墙时关闭的。公司整体对外会设置非常安全的防火墙   
#### 3）创建 atguigu 用户，并修改 atguigu 用户的密码
```shell
[root@hadoop100 ~]# useradd atguigu
[root@hadoop100 ~]# passwd atguigu
```
#### 4）配置 atguigu 用户具有 root 权限，方便后期加 sudo 执行 root 权限的命令
```shell
[root@hadoop100 ~]# vim /etc/sudoers
```
修改/etc/sudoers 文件，在%wheel 这行下面添加一行，如下所示：
```shell
## Allow root to run any commands anywhere

root ALL=(ALL) ALL

## Allows people in group wheel to run all commands

%wheel ALL=(ALL) ALL
atguigu ALL=(ALL) NOPASSWD:ALL
```
注意：atguigu 这一行不要直接放到 root 行下面，因为所有用户都属于 wheel 组，你先配置了 atguigu 具有免密功能，但是程序执行到%wheel 行时，该功能又被覆盖回需要密码。所以 atguigu 要放到%wheel 这行下面。   
#### 5）在/opt 目录下创建文件夹，并修改所属主和所属组
（1）在/opt 目录下创建 module、software 文件夹
```shell
[root@hadoop100 ~]# mkdir /opt/module
[root@hadoop100 ~]# mkdir /opt/software
```
（2）修改 module、software 文件夹的所有者和所属组均为 atguigu 用户
```shell
[root@hadoop100 ~]# chown atguigu:atguigu /opt/module
[root@hadoop100 ~]# chown atguigu:atguigu /opt/software
```
（3）查看 module、software 文件夹的所有者和所属组
```shell
[root@hadoop100 ~]# cd /opt/
[root@hadoop100 opt]# ll
总用量 12
drwxr-xr-x. 2 atguigu atguigu 4096 5 月 28 17:18 module
drwxr-xr-x. 2 root root 4096 9 月 7 2017 rh
drwxr-xr-x. 2 atguigu atguigu 4096 5 月 28 17:18 software
```
#### 6）卸载虚拟机自带的 JDK
注意：如果你的虚拟机是最小化安装不需要执行这一步。
```shell
[root@hadoop100 ~]# rpm -qa | grep -i java | xargs -n1 rpm -e
--nodeps
```
➢ rpm -qa：查询所安装的所有 rpm 软件包   
➢ grep -i：忽略大小写   
➢ xargs -n1：表示每次只传递一个参数   
➢ rpm -e –nodeps：强制卸载软件
#### 7）安装jdk
（1）卸载现有 JDK   
（2）将jdk上传到`/opt/software/`   
（3）解压 JDK 到`/opt/module` 目录下
```shell
[atguigu@hadoop102 software]$ tar -zxvf jdk-8u212-linux-x64.tar.gz -C /opt/module/
```
（4）配置环境变量   
新建/etc/profile.d/my_env.sh 文件并添加如下内容，保存后退出
```shell
[atguigu@hadoop102 ~]$ sudo vim /etc/profile.d/my_env.sh
#JAVA_HOME
export JAVA_HOME=/opt/module/jdk1.8.0_212
export PATH=$PATH:$JAVA_HOME/bin
```
source 一下/etc/profile 文件，让新的环境变量 PATH 生效
```shell
[atguigu@hadoop102 ~]$ source /etc/profile
```
测试jdk是否安装成功，如果不生效可以重启
#### 8）安装Hadoop
Hadoop 下载地址：[https://archive.apache.org/dist/hadoop/common/hadoop-3.1.3/](https://archive.apache.org/dist/hadoop/common/hadoop-3.1.3/)   
将Hadoop上传到`/opt/software/`，并解压到`/opt/module`
```shell
[atguigu@hadoop102 software]$ tar -zxvf hadoop-3.1.3.tar.gz -C /opt/module/
```
将Hadoop添加到环境变量，编辑`/etc/profile.d/my_env.sh`文件
```shell
[atguigu@hadoop102 hadoop-3.1.3]$ sudo vim /etc/profile.d/my_env.sh
#HADOOP_HOME
export HADOOP_HOME=/opt/module/hadoop-3.1.3
export PATH=$PATH:$HADOOP_HOME/bin
export PATH=$PATH:$HADOOP_HOME/sbin
[atguigu@hadoop102 hadoop-3.1.3]$ source /etc/profile
[atguigu@hadoop102 hadoop-3.1.3]$ hadoop version
Hadoop 3.1.3
```
如果不生效，可以重启尝试   
   
查看 Hadoop 目录结构
```shell
[atguigu@hadoop102 hadoop-3.1.3]$ ll
总用量 52
drwxr-xr-x. 2 atguigu atguigu 4096 5 月 22 2017 bin
drwxr-xr-x. 3 atguigu atguigu 4096 5 月 22 2017 etc
drwxr-xr-x. 2 atguigu atguigu 4096 5 月 22 2017 include
drwxr-xr-x. 3 atguigu atguigu 4096 5 月 22 2017 lib
drwxr-xr-x. 2 atguigu atguigu 4096 5 月 22 2017 libexec
-rw-r--r--. 1 atguigu atguigu 15429 5 月 22 2017 LICENSE.txt
-rw-r--r--. 1 atguigu atguigu 101 5 月 22 2017 NOTICE.txt
-rw-r--r--. 1 atguigu atguigu 1366 5 月 22 2017 README.txt
drwxr-xr-x. 2 atguigu atguigu 4096 5 月 22 2017 sbin
drwxr-xr-x. 4 atguigu atguigu 4096 5 月 22 2017 share
```
> （1）bin 目录：存放对 Hadoop 相关服务（hdfs，yarn，mapred）进行操作的脚本
> （2）etc 目录：Hadoop 的配置文件目录，存放 Hadoop 的配置文件
> （3）lib 目录：存放 Hadoop 的本地库（对数据进行压缩解压缩功能）
> （4）sbin 目录：存放启动或停止 Hadoop 相关服务的脚本
> （5）share 目录：存放 Hadoop 的依赖 jar 包、文档、和官方案例
#### 9）配置网络
（1）查看 Linux 虚拟机的虚拟网络编辑器，编辑->虚拟网络编辑器->VMnet8   
![虚拟网络编辑器-1](img/011-虚拟网络编辑器-1.png)   
![虚拟网络编辑器-2](img/012-虚拟网络编辑器-2.png)   
（2）查看 Windows 系统适配器 VMware Network Adapter VMnet8 的 IP 地址
![Windows 系统适配器-1](img/013-Windows%20系统适配器-1.png)
![Windows 系统适配器-2](img/014-Windows%20系统适配器-2.png)
![Windows 系统适配器-3](img/015-Windows%20系统适配器-3.png)
![Windows 系统适配器-4](img/016-Windows%20系统适配器-4.png)
（3）保证 Linux 系统 ifcfg-ens33 文件中 IP 地址、虚拟网络编辑器地址和 Windows 系统 VM8 网络 IP 地址相同。   
（4）修改模板虚拟机的静态 IP
```shell
[root@hadoop100 ~]# vim /etc/sysconfig/network-scripts/ifcfg-ens33
```
改成
```shell
DEVICE=ens33
TYPE=Ethernet
ONBOOT=yes
BOOTPROTO=static
NAME="ens33"
IPADDR=192.168.10.100
GATEWAY=192.168.10.2
DNS1=192.168.10.2
```
（5）修改主机名称
```shell
[root@hadoop100 ~]# vim /etc/hostname
hadoop100
```
（6）配置 Linux 克隆机主机名称映射 hosts 文件，打开/etc/hosts
添加如下内容
```shell
[root@hadoop100 ~]# vim /etc/hosts
192.168.10.100 hadoop100
192.168.10.101 hadoop101
192.168.10.102 hadoop102
192.168.10.103 hadoop103
192.168.10.104 hadoop104
192.168.10.105 hadoop105
192.168.10.106 hadoop106
192.168.10.107 hadoop107
192.168.10.108 hadoop108
```
（7）修改Windows hosts文件   
进入 `C:\Windows\System32\drivers\etc` 路径   
修改`hosts`文件
```
192.168.10.100 hadoop100
192.168.10.101 hadoop101
192.168.10.102 hadoop102
192.168.10.103 hadoop103
192.168.10.104 hadoop104
192.168.10.105 hadoop105
192.168.10.106 hadoop106
192.168.10.107 hadoop107
192.168.10.108 hadoop108
```
#### 10）重启虚拟机
```shell
[root@hadoop100 ~]# reboot
```
#### 11）关机克隆   
![克隆虚拟机-1](img/017-克隆虚拟机-1.png)   
![克隆虚拟机-2](img/018-克隆虚拟机-2.png)
### 2.2 克隆虚拟机
#### 1）利用模板机 hadoop100，克隆三台虚拟机：hadoop102 hadoop103 hadoop104
注意：克隆时，要先关闭 hadoop100
#### 2）修改克隆机 IP，以下以 hadoop102 举例说明
（1）修改克隆虚拟机的静态 IP
```shell
[root@hadoop100 ~]# vim /etc/sysconfig/network-scripts/ifcfg-ens33
```
改成
```shell
DEVICE=ens33
TYPE=Ethernet
ONBOOT=yes
BOOTPROTO=static
NAME="ens33"
IPADDR=192.168.10.102
PREFIX=24
GATEWAY=192.168.10.2
DNS1=192.168.10.2
```


## 三、Hadoop运行模式
1）Hadoop官方网站：[http://hadoop.apache.org/](http://hadoop.apache.org/)   
2）Hadoop运行模式：   
➢ 本地模式：单机运行，只是用来演示一下官方案例。生产环境不用。   
➢ 伪分布式模式：也是单机运行，但是具备 Hadoop 集群的所有功能，一台服务器模拟一个分布式的环境。个别缺钱的公司用来测试，生产环境不用。   
➢ 完全分布式模式：多台服务器组成分布式环境。生产环境使用。
### 3.1 本地运行模式（官方 WordCount）
1）创建在 hadoop-3.1.3 文件下面创建一个 wcinput 文件夹   
2）在 wcinput 文件下创建一个 word.txt 文件   
3）编辑 word.txt 文件   
4）回到 Hadoop 目录/opt/module/hadoop-3.1.3   
5）执行程序   
6）查看结果
```shell
[atguigu@hadoop102 hadoop-3.1.3]$ mkdir wcinput
[atguigu@hadoop102 hadoop-3.1.3]$ cd wcinput
[atguigu@hadoop102 wcinput]$ vim word.txt
hadoop yarn
hadoop mapreduce
atguigu
atguigu
[atguigu@hadoop102 hadoop-3.1.3]$ hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.1.3.jar wordcount wcinput wcoutput
[atguigu@hadoop102 hadoop-3.1.3]$ cat wcoutput/part-r-00000
atguigu 2
hadoop 2
mapreduce 1
yarn 1
```
### 3.2 完全分布式运行模式（开发重点）
分析：
1）准备 3 台客户机（关闭防火墙、静态 IP、主机名称）    
2）安装 JDK   
3）配置环境变量   
4）安装 Hadoop   
5）配置环境变量   
6）配置集群   
7）单点启动   
8）配置 ssh   
9）群起并测试集群   
#### 3.2.1 虚拟机准备
#### 3.2.2 编写集群分发脚本 `xsync`
##### 1）scp（secure copy）安全拷贝
（1）scp 定义   
scp 可以实现服务器与服务器之间的数据拷贝。（from server1 to server2）    
（2）基本语法   
`scp -r $pdir/$fname $user@$host:$pdir/$fname`   
命令 递归 要拷贝的文件路径/名称 目的地用户@主机:目的地路径/名称   
（3）案例实操   
➢ 前提：在 `hadoop102`、`hadoop103`、`hadoop104` 都已经创建好的`/opt/module`、`/opt/software` 两个目录，并且已经把这两个目录修改为 `atguigu:atguigu`
```shell
[atguigu@hadoop102 ~]$ sudo chown atguigu:atguigu -R /opt/module
```
（a）在 `hadoop102` 上，将 `hadoop102` 中`/opt/module/jdk1.8.0_212` 目录拷贝到`hadoop103` 上。
```shell
[atguigu@hadoop102 ~]$ scp -r /opt/module/jdk1.8.0_212 atguigu@hadoop103:/opt/module
```
（b）在 `hadoop103` 上，将 `hadoop102` 中`/opt/module/hadoop-3.1.3` 目录拷贝到`hadoop103` 上。
```shell
[atguigu@hadoop103 ~]$ scp -r atguigu@hadoop102:/opt/module/hadoop-3.1.3 /opt/module/
```
（c）在 `hadoop103` 上操作，将 `hadoop102` 中`/opt/module` 目录下所有目录拷贝到`hadoop104` 上。
```shell
[atguigu@hadoop103 opt]$ scp -r atguigu@hadoop102:/opt/module/* atguigu@hadoop104:/opt/module
```
##### 2）rsync 远程同步工具   
rsync 主要用于备份和镜像。具有速度快、避免复制相同内容和支持符号链接的优点。   
rsync 和 scp 区别：用 rsync 做文件的复制要比 scp 的速度快，rsync 只对差异文件做更新。scp 是把所有文件都复制过去。   
（1）基本语法
`rsync -av $pdir/$fname $user@$host:$pdir/$fname`   
命令 选项参数 要拷贝的文件路径/名称 目的地用户@主机:目的地路径/名称   

| 选项      | 功能    | 
| :---: | :---:  | 
| -a  | 归档拷贝 |
| -v  | 显示复制过程 |

（2）案例实操   
（a）删除 hadoop103 中/opt/module/hadoop-3.1.3/wcinput   
（b）同步 hadoop102 中的/opt/module/hadoop-3.1.3 到 hadoop103   
```shell
[atguigu@hadoop103 hadoop-3.1.3]$ rm -rf wcinput/
[atguigu@hadoop102 module]$ rsync -av hadoop-3.1.3/ atguigu@hadoop103:/opt/module/hadoop-3.1.3/
```
##### 3）xsync 集群分发脚本
（1）需求：循环复制文件到所有节点的相同目录下   
（2）需求分析：   
（a）rsync 命令原始拷贝：   
`rsync -av /opt/module atguigu@hadoop103:/opt/`
（b）期望脚本：   
xsync 要同步的文件名称   
（c）期望脚本在任何路径都能使用（脚本放在声明了全局环境变量的路径）
```shell
[atguigu@hadoop102 ~]$ echo $PATH /usr/local/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/home/atguigu/.local/bin:/home/atguigu/bin:/opt/module/jdk1.8.0_212/bin
```
（3）脚本实现   
（a）在/home/atguigu/bin 目录下创建 xsync 文件
```shell
[atguigu@hadoop102 opt]$ cd /home/atguigu
[atguigu@hadoop102 ~]$ mkdir bin
[atguigu@hadoop102 ~]$ cd bin
[atguigu@hadoop102 bin]$ vim xsync
```
在该文件中编写如下代码
```shell
#!/bin/bash

#1. 判断参数个数
if [ $# -lt 1 ]
then
    echo Not Enough Arguement!
    exit;
fi

#2. 遍历集群所有机器
for host in hadoop102 hadoop103 hadoop104
do
    echo ====================  $host  ====================
    #3. 遍历所有目录，挨个发送

    for file in $@
    do
        #4. 判断文件是否存在
        if [ -e $file ]
            then
                #5. 获取父目录
                pdir=$(cd -P $(dirname $file); pwd)

                #6. 获取当前文件的名称
                fname=$(basename $file)
                ssh $host "mkdir -p $pdir"
                rsync -av $pdir/$fname $host:$pdir
            else
                echo $file does not exists!
        fi
    done
done
```
（b）修改脚本 xsync 具有执行权限   
`[atguigu@hadoop102 bin]$ chmod +x xsync`   
（c）测试脚本   
`[atguigu@hadoop102 ~]$ xsync /home/atguigu/bin`   
（d）将脚本复制到/bin 中，以便全局调用   
`[atguigu@hadoop102 bin]$ sudo cp xsync /bin/`   
（e）同步环境变量配置（root 所有者）   
`[atguigu@hadoop102 ~]$ sudo ./bin/xsync /etc/profile.d/my_env.sh`   
注意：如果用了 sudo，那么 xsync 一定要给它的路径补全。让环境变量生效   
`[atguigu@hadoop103 bin]$ source /etc/profile`   
`[atguigu@hadoop104 opt]$ source /etc/profile`   

#### 3.2.3 SSH无密登录配置
##### 1）配置 ssh
（1）基本语法   
ssh 另一台电脑的 IP 地址   
（2）ssh 连接时出现 Host key verification failed 的解决方法   
`[atguigu@hadoop102 ~]$ ssh hadoop103`   
➢ 如果出现如下内容   
`Are you sure you want to continue connecting (yes/no)?`   
➢ 输入 yes，并回车   
（3）退回到 hadoop102   
`[atguigu@hadoop103 ~]$ exit`   
##### 2）无密钥配置
（1）免密登录原理   
![img.png](img/019-ssh免登陆原理.png)   
（2）生成公钥和私钥
```shell
[atguigu@hadoop102 .ssh]$ pwd /home/atguigu/.ssh
[atguigu@hadoop102 .ssh]$ ssh-keygen -t rsa
```
然后敲（三个回车），就会生成两个文件 id_rsa（私钥）、id_rsa.pub（公钥）
（3）将公钥拷贝到要免密登录的目标机器上
```shell
[atguigu@hadoop102 .ssh]$ ssh-copy-id hadoop102
[atguigu@hadoop102 .ssh]$ ssh-copy-id hadoop103
[atguigu@hadoop102 .ssh]$ ssh-copy-id hadoop104
```
注意：   
还需要在 hadoop103 上采用 atguigu 账号配置一下无密登录到 hadoop102、hadoop103、hadoop104 服务器上。   
还需要在 hadoop104 上采用 atguigu 账号配置一下无密登录到hadoop102、hadoop103、hadoop104 服务器上。   
还需要在 hadoop102 上采用 root 账号，配置一下无密登录到 hadoop102、hadoop103、hadoop104；   
3）.ssh 文件夹下（~/.ssh）的文件功能解释

| 文件名 | 作用 |
| :---: | :---: |
| known_hosts | 记录ssh访问过计算机的公钥（public key） |
| id_rsa | 生成的私钥 |
| id_rsa.pub | 生成的公钥 |
| authorized_keys | 存放授权过的无密登录服务器公钥 |

#### 3.2.4 集群配置
##### 1）集群部署规划
注意：   
➢ NameNode 和 SecondaryNameNode 不要安装在同一台服务器   
➢ ResourceManager 也很消耗内存，不要和 NameNode、SecondaryNameNode 配置在同一台机器上。   

|   | hadoop102 | hadoop103 | hadoop104 |
| :---: | :---: | :---: | :---: |
| HDFS | NameNode、DataNode | DataNode | SecondaryNameNode、DataNode |
| YARN | NodeManager | ResourceManager、NodeManager | NodeManager |
##### 2）配置文件说明
Hadoop 配置文件分两类：默认配置文件和自定义配置文件，只有用户想修改某一默认配置值时，才需要修改自定义配置文件，更改相应属性值。   
（1）默认配置文件：   

| 要获取的默认文件 | 文件存放在Hadoop的jar包中的位置 |
| :---: | :---: |
| [core-default.xml](img/020-core-default.xml) | hadoop-common-3.1.3.jar/core-default.xml |
| [hdfs-default.xml](img/021-hdfs-default.xml) | hadoop-hdfs-3.1.3.jar/hdfs-default.xml |
| [yarn-default.xml](img/022-yarn-default.xml) | hadoop-yarn-common-3.1.3.jar/yarn-default.xml |
| [mapred-default.xml](img/023-mapred-default.xml) | hadoop-mapreduce-client-core-3.1.3.jar/mapred-default.xml |

（2）自定义配置文件：   
core-site.xml、hdfs-site.xml、yarn-site.xml、mapred-site.xml 四个配置文件存放在 `$HADOOP_HOME/etc/hadoop` 这个路径上，用户可以根据项目需求重新进行修改配置。   

##### 3）配置集群
（1）核心配置文件   
配置 core-site.xml
```shell
[atguigu@hadoop102 ~]$ cd $HADOOP_HOME/etc/hadoop
[atguigu@hadoop102 hadoop]$ vim core-site.xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>

<configuration>
    <!-- 指定NameNode的地址 -->
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://hadoop102:8020</value>
    </property>

    <!-- 指定hadoop数据的存储目录 -->
    <property>
        <name>hadoop.tmp.dir</name>
        <value>/opt/module/hadoop-3.1.3/data</value>
    </property>

    <!-- 配置HDFS网页登录使用的静态用户为atguigu -->
    <property>
        <name>hadoop.http.staticuser.user</name>
        <value>atguigu</value>
    </property>
</configuration>
```
（2）HDFS 配置文件   
配置 hdfs-site.xml
```shell
[atguigu@hadoop102 hadoop]$ vim hdfs-site.xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>

<configuration>
    <!-- nn web端访问地址-->
    <property>
        <name>dfs.namenode.http-address</name>
        <value>hadoop102:9870</value>
    </property>
    <!-- 2nn web端访问地址-->
    <property>
        <name>dfs.namenode.secondary.http-address</name>
        <value>hadoop104:9868</value>
    </property>
</configuration>
```
（3）YARN 配置文件   
配置 yarn-site.xml
```shell
[atguigu@hadoop102 hadoop]$ vim yarn-site.xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>

<configuration>
    <!-- 指定MR走shuffle -->
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>

    <!-- 指定ResourceManager的地址-->
    <property>
        <name>yarn.resourcemanager.hostname</name>
        <value>hadoop103</value>
    </property>

    <!-- 环境变量的继承 -->
    <property>
        <name>yarn.nodemanager.env-whitelist</name>
        <value>JAVA_HOME,HADOOP_COMMON_HOME,HADOOP_HDFS_HOME,HADOOP_CONF_DIR,CLASSPATH_PREPEND_DISTCACHE,HADOOP_YARN_HOME,HADOOP_MAPRED_HOME</value>
    </property>
</configuration>
```
（4）MapReduce 配置文件   
配置 mapred-site.xml
```shell
[atguigu@hadoop102 hadoop]$ vim mapred-site.xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>

<configuration>
    <!-- 指定MapReduce程序运行在Yarn上 -->
    <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
</configuration>
```
##### 4）在集群上分发配置好的Hadoop配置文件
`[atguigu@hadoop102 hadoop]$ xsync /opt/module/hadoop-3.1.3/etc/hadoop/`
##### 5）去103和104上查看文件分发情况
`[atguigu@hadoop103 ~]$ cat /opt/module/hadoop-3.1.3/etc/hadoop/core-site.xml`
`[atguigu@hadoop104 ~]$ cat /opt/module/hadoop-3.1.3/etc/hadoop/core-site.xml`

#### 3.2.5 群起集群
##### 1）配置 workers
```shell
[atguigu@hadoop102 hadoop]$ vim /opt/module/hadoop-3.1.3/etc/hadoop/workers
hadoop102
hadoop103
hadoop104
```
注意：该文件中添加的内容结尾不允许有空格，文件中不允许有空行。   
同步所有节点配置文件   
`[atguigu@hadoop102 hadoop]$ xsync /opt/module/hadoop-3.1.3/etc`
##### 2）启动集群
（1）如果集群是第一次启动，需要在 hadoop102 节点格式化 NameNode（注意：格式化 NameNode，会产生新的集群 id，导致 NameNode 和 DataNode 的集群 id 不一致，集群找不到已往数据。如果集群在运行过程中报错，需要重新格式化 NameNode 的话，一定要先停 止 namenode 和 datanode 进程，并且要删除所有机器的 data 和 logs 目录，然后再进行格式化。）   
`[atguigu@hadoop102 hadoop-3.1.3]$ hdfs namenode -format`
（2）启动 HDFS   
`[atguigu@hadoop102 hadoop-3.1.3]$ sbin/start-dfs.sh`   
（3）在配置了 ResourceManager 的节点（hadoop103）启动 YARN   
`[atguigu@hadoop103 hadoop-3.1.3]$ sbin/start-yarn.sh`   
（4）Web 端查看 HDFS 的 NameNode   
➢ （a）浏览器中输入：[http://hadoop102:9870](http://hadoop102:9870)   
➢ （b）查看 HDFS 上存储的数据信息   
（5）Web 端查看 YARN 的 ResourceManager   
➢ （a）浏览器中输入：[http://hadoop103:8088](http://hadoop103:8088)   
➢ （b）查看 YARN 上运行的 Job 信息   
##### 3）集群基本测试
（1）上传文件到集群
➢ 上传小文件
```shell
[atguigu@hadoop102 ~]$ hadoop fs -mkdir /input
[atguigu@hadoop102 ~]$ hadoop fs -put $HADOOP_HOME/wcinput/word.txt /input
```
➢ 上传大文件   
`[atguigu@hadoop102 ~]$ hadoop fs -put /opt/software/jdk-8u212-linux-x64.tar.gz / `   
（2）上传文件后查看文件存放在什么位置   
➢ 查看 HDFS 文件存储路径   
`[atguigu@hadoop102 subdir0]$ pwd /opt/module/hadoop-3.1.3/data/dfs/data/current/BP-1436128598-192.168.10.102-1610603650062/current/finalized/subdir0/subdir0`   
➢ 查看 HDFS 在磁盘存储文件内容   
```shell
[atguigu@hadoop102 subdir0]$ cat blk_1073741825
hadoop yarn
hadoop mapreduce
atguigu
atguigu
```
（3）拼接
```shell
-rw-rw-r--. 1 atguigu atguigu 134217728 5 月 23 16:01 blk_1073741836
-rw-rw-r--. 1 atguigu atguigu 1048583 5 月 23 16:01 blk_1073741836_1012.meta
-rw-rw-r--. 1 atguigu atguigu 63439959 5 月 23 16:01 blk_1073741837
-rw-rw-r--. 1 atguigu atguigu 495635 5 月 23 16:01 blk_1073741837_1013.meta
[atguigu@hadoop102 subdir0]$ cat blk_1073741836>>tmp.tar.gz
[atguigu@hadoop102 subdir0]$ cat blk_1073741837>>tmp.tar.gz
[atguigu@hadoop102 subdir0]$ tar -zxvf tmp.tar.gz
```
（4）下载   
`[atguigu@hadoop104 software]$ hadoop fs -get /jdk-8u212-linux-x64.tar.gz ./`   
（5）执行 wordcount 程序   
`[atguigu@hadoop102 hadoop-3.1.3]$ hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.1.3.jar wordcount /input /output`

#### 3.2.6 配置历史服务器   
为了查看程序的历史运行情况，需要配置一下历史服务器。具体配置步骤如下：   
##### 1）配置 mapred-site.xml
```shell
[atguigu@hadoop102 hadoop]$ vim mapred-site.xml
<!-- 历史服务器端地址 -->
<property>
    <name>mapreduce.jobhistory.address</name>
    <value>hadoop102:10020</value>
</property>

<!-- 历史服务器web端地址 -->
<property>
    <name>mapreduce.jobhistory.webapp.address</name>
    <value>hadoop102:19888</value>
</property>
```
##### 2）分发配置
`[atguigu@hadoop102 hadoop]$ xsync $HADOOP_HOME/etc/hadoop/mapred-site.xml`   
##### 3）在 hadoop102 启动历史服务器
`[atguigu@hadoop102 hadoop]$ mapred --daemon start historyserver`   
##### 4）查看历史服务器是否启动
`[atguigu@hadoop102 hadoop]$ jps`
##### 5）查看 JobHistory
[http://hadoop102:19888/jobhistory](http://hadoop102:19888/jobhistory)

#### 3.2.7 配置日志的聚集
日志聚集概念：应用运行完成以后，将程序运行日志信息上传到 HDFS 系统上。   
![配置日志的聚集](img/024-配置日志的聚集.png)   
日志聚集功能好处：可以方便的查看到程序运行详情，方便开发调试。   
注意：开启日志聚集功能，需要重新启动 NodeManager 、ResourceManager 和 HistoryServer。   
开启日志聚集功能具体步骤如下：
1）配置yarn-site.xml
```shell
[atguigu@hadoop102 hadoop]$ vim yarn-site.xml
<!-- 开启日志聚集功能 -->
<property>
    <name>yarn.log-aggregation-enable</name>
    <value>true</value>
</property>
<!-- 设置日志聚集服务器地址 -->
<property>  
    <name>yarn.log.server.url</name>  
    <value>http://hadoop102:19888/jobhistory/logs</value>
</property>
<!-- 设置日志保留时间为7天 -->
<property>
    <name>yarn.log-aggregation.retain-seconds</name>
    <value>604800</value>
</property>
```
2）分发配置   
`[atguigu@hadoop102 hadoop]$ xsync $HADOOP_HOME/etc/hadoop/yarn-site.xml`   
3）管理NodeManager、ResourceManager和HistoryServer   
```shell
[atguigu@hadoop103 hadoop-3.1.3]$ sbin/stop-yarn.sh
[atguigu@hadoop103 hadoop-3.1.3]$ mapred --daemon stop historyserver
```
4）启动NodeManager、ResourceManager和HistoryServer
```shell
[atguigu@hadoop103 ~]$ start-yarn.sh
[atguigu@hadoop102 ~]$ mapred --daemon start historyserver
```
5）删除HDFS上已经存在的输出文件   
`[atguigu@hadoop102 ~]$ hadoop fs -rm -r /output`   
6）执行WordCount程序
`[atguigu@hadoop102 hadoop-3.1.3]$ hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.1.3.jar wordcount /input /output`
7）查看日志   
历史服务器地址：[http://hadoop102:19888/jobhistory](http://hadoop102:19888/jobhistory)   
历史任务列表：   
![历史任务列表](img/025-历史任务列表.png)   
查看任务运行日志：   
![查看任务运行日志](img/026-查看任务运行日志.png)
运行日志详情：   
![运行日志详情](img/027-运行日志详情.png)

#### 3.2.8 集群启动/停止方法总结
1）各个模块分开启动/停止（配置 ssh 是前提）常用   
整体启动/停止 HDFS   
`start-dfs.sh/stop-dfs.sh`   
整体启动/停止 YARN   
`start-yarn.sh/stop-yarn.sh`   
2）各个服务组件逐一启动/停止   
分别启动/停止 HDFS 组件
`hdfs --daemon start/stop namenode/datanode/secondarynamenode`   
启动/停止 YARN
`yarn --daemon start/stop resourcemanager/nodemanager`   

#### 3.2.9 编写 Hadoop 集群常用脚本
1）Hadoop 集群启停脚本（包含 HDFS，Yarn，Historyserver）：myhadoop.sh，然后赋予脚本执行权限   
```shell
[atguigu@hadoop102 ~]$ cd /home/atguigu/bin
[atguigu@hadoop102 bin]$ vim myhadoop.sh
#!/bin/bash

if [ $# -lt 1 ]
then
    echo "No Args Input..."
    exit ;
fi

case $1 in
"start")
        echo " =================== 启动 hadoop集群 ==================="

        echo " --------------- 启动 hdfs ---------------"
        ssh hadoop102 "/opt/module/hadoop-3.1.3/sbin/start-dfs.sh"
        echo " --------------- 启动 yarn ---------------"
        ssh hadoop103 "/opt/module/hadoop-3.1.3/sbin/start-yarn.sh"
        echo " --------------- 启动 historyserver ---------------"
        ssh hadoop102 "/opt/module/hadoop-3.1.3/bin/mapred --daemon start historyserver"
;;
"stop")
        echo " =================== 关闭 hadoop集群 ==================="

        echo " --------------- 关闭 historyserver ---------------"
        ssh hadoop102 "/opt/module/hadoop-3.1.3/bin/mapred --daemon stop historyserver"
        echo " --------------- 关闭 yarn ---------------"
        ssh hadoop103 "/opt/module/hadoop-3.1.3/sbin/stop-yarn.sh"
        echo " --------------- 关闭 hdfs ---------------"
        ssh hadoop102 "/opt/module/hadoop-3.1.3/sbin/stop-dfs.sh"
;;
*)
    echo "Input Args Error..."
;;
esac
[atguigu@hadoop102 bin]$ chmod +x myhadoop.sh
```
2）查看三台服务器 Java 进程脚本：jpsall，然后赋予脚本权限   
```shell
[atguigu@hadoop102 ~]$ cd /home/atguigu/bin
[atguigu@hadoop102 bin]$ vim jpsall
#!/bin/bash

for host in hadoop102 hadoop103 hadoop104
do
        echo =============== $host ===============
        ssh $host jps 
done
[atguigu@hadoop102 bin]$ chmod +x jpsall
```
3）分发/home/atguigu/bin 目录，保证自定义脚本在三台机器上都可以使用   
`[atguigu@hadoop102 ~]$ xsync /home/atguigu/bin/`   
#### 3.2.10 常用端口号说明（面试题）
| 端口名称 | Hadoop2.x | Hadoop3.x |
| :---: | :---: | :---: |
| NameNode内部通信端口 | 8020/9000 | 8020/9000/9820 |
| NameNode HTTP UI | 50070 | 9870 |
| MapReduce查看执行任务端口 | 8088 | 8088 |
| 历史服务器通信端口 | 19888 | 19888 |

## 四、常见错误及解决方案
### 4.1 启动后提示没有JAVA_HOME
在 `$HADOOP_HOME//etc/hadoop/hadoop-env.sh` 中的 `export JAVA_HOME=` 后手动填写 `JAVA_HOME` 的值。
### 4.2 提示 `there is no HDFS_NAMENODE_USER defined`
```shell
[root@master sbin]# ./start-dfs.sh
Starting namenodes on [master]
ERROR: Attempting to operate on hdfs namenode as root
ERROR: but there is no HDFS_NAMENODE_USER defined. Aborting operation.
Starting datanodes
ERROR: Attempting to operate on hdfs datanode as root
ERROR: but there is no HDFS_DATANODE_USER defined. Aborting operation.
Starting secondary namenodes [slave1]
ERROR: Attempting to operate on hdfs secondarynamenode as root
ERROR: but there is no HDFS_SECONDARYNAMENODE_USER defined. Aborting operation.
```
解决方式：   
在 `$HADOOP_HOME/sbin` 路径下，将 `start-dfs.sh` 、 `stop-dfs.sh` 两个文件**顶部**添加以下参数
```shell
HDFS_DATANODE_USER=root
HADOOP_SECURE_DN_USER=hdfs
HDFS_NAMENODE_USER=root
HDFS_SECONDARYNAMENODE_USER=root
```
start-yarn.sh，stop-yarn.sh顶部添加以下参数：
```shell
YARN_RESOURCEMANAGER_USER=root
HADOOP_SECURE_DN_USER=yarn
YARN_NODEMANAGER_USER=root
```
修改保存后重启Hadoop即可解决
### 4.3 DataNode不启动
注意看 `/opt/module/hadoop-3.1.3/data/dfs/data/current/VERSION` 的内容是否相同，如果不一致就将`data`和`logs`路径删除，并重新初始化。
### 4.4 未完待续...


