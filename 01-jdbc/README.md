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

```java
import java.sql.*;

public class MySQLTest {

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1、 注册驱动
            Driver driver = new com.mysql.jc.jdbc.Driver();
            DriverManager.registerDriver(driver);

            // 2、 获取数据库连接对象
            /*
               URL：统一资源定位符，格式为“协议://IP地址:端口号/资源名” 
               
               协议：是一个提前规定好的数据传输格式。通信协议有很多：http、https......
               在传送数据之前，提前先商量好数据传送的格式
               这样对方接收到数据之后，就会按照这个格式去解析，拿到有价值的数据
               
               IP地址：网络当中定位某台计算机的
               
               PORT端口号：定位指定计算机上某个服务的
               
               资源名：定位指定服务下的某个资源
               
               "jdbc:mysql://" ------ java程序和mysql通信的协议
               "localhost" ------ 本机IP地址，也可以写为127.0.0.1
               "3306" ------ mysql数据库端口号
               "database" ------ mysql数据库的名称
             */
            String url = "jdbc:mysql://ip_address:port/database";
            String user = "";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);

            // 3、获取数据库操作对象（通过一个Connection对象可以获取多个操作对象）
            stmt = conn.createStatement();

            // 4、执行SQL语句
            String insertSql = "insert into stu(stu_id, stu_name) values (01, '小明')";
            /*
               int executeUpdate(String sql)
               执行给定的SQL语句，这可能是 INSERT ， UPDATE ，或 DELETE语句，或者不返回任何内容，如SQL DDL语句的SQL语句。返回值表示影响的行数。
             */
            int count = stmt.executeUpdate(insertSql);
            System.out.println(count);

            // =======================================

            // 5、处理查询结果集
            String selectSql = "select stu_no, stu_name from stu order by stu_no desc";
            // ResultSet就是查询结果集对象，查询的结果都在这个对象当中
            rs = stmt.executeQuery(selectSql);
            while (rs.next()) {
                // 按照索引取值，jdbc中所有的下表都是从1开始的
                String stu_no_index = rs.getString(1);
                String stu_name_index = rs.getString(2);
                
                // 按照列明取值
                String stu_no_column = rs.getString("stu_no");
                String stu_name_column = rs.getString("stu_name");
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 6、 释放资源（依次释放ResultSet对象、Statement对象、Connection对象，分别进行try、catch处理，放到finally中关闭）
            if (null != rs) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != stmt) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
```

#### 5、注册驱动的第二种方式
[代码](./src/club/kwcoder/jdbc/JDBCTest02.java)

#### 6、使用配置文件写连接信息
[代码](./src/club/kwcoder/jdbc/JDBCTest03.java)

#### 7、模拟SQL注入
[代码](./src/club/kwcoder/jdbc/JDBCTest04.java)
通过输入特定的字符，可以扭曲SQL语义，导致出现SQL注入的情况
![img.png](../img/img.png)
导致SQL注入的根本原因：用户是懂程序的，输入的用户名信息以及密码中含有SQL语句的关键字，这个SQL语句的关键字和底层的SQL语句进行“字符串拼接”，导致原SQL语句的含义被扭曲了。最主要的原因是用户提供的字符串参与了SQL语句的编译。