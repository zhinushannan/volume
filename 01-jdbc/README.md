# JDBC复习笔记

#### 1、什么是JDBC？
Java DataBase Connectivity   
在Java语言中编写sql语句，对mysql数据库中的数据进行CRUD操作。

#### 2、JDBC相关的类库所在位置？
java.sql.*

#### 3、JDBC本质上是什么？
```java
// jdbc实际上是SUN公司制定好的一套接口，纯interface，
// 由各个数据库厂商对其进行实现

public interface JDBC {
    void getConnection();
}

public class MySQL implements JDBC {
    public void getContention() {
        // MySQL厂商对接口进行实现
    }
}

public class Oracle implements JDBC {
    public void getContention() {
        // Oracle厂商对接口进行实现
    }
}

```
SUN公司通过制定统一接口，降低了耦合度，提高了扩展能力，使程序开发者不再关注数据库的品牌问题，是需要面向JDBC接口编程即可。

1. SUN公司负责制定JDBC接口
2. 各个数据库厂商负责实现JDBC接口
3. 开发人员调用接口操作数据库

#### 4、JDBC编程六步
1. 注册驱动（通知Java程序我们即将要链接的是哪个品牌的数据库）
2. 获取数据库连接（Java进程和MySQL进程，两个进程之间的通道开启了）（Java进程可能在北京，MySQL进程可能在上海）
3. 获取数据库操作对象（这个对象很重要，用来执行SQL语句）
4. 执行SQL语句（执行CRUD操作）
5. 处理查询结果集（如果第四步为SELECT，才有第五步）
6. 释放资源（关闭所有资源，因为JDBC是进程之间的通信，占用很多资源，需要关闭！）
