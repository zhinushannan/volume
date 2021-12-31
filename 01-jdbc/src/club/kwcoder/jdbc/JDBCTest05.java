package club.kwcoder.jdbc;

import club.kwcoder.jdbc.utils.JDBCUtil;

import java.sql.*;
import java.util.Scanner;

/**
 * java.sql.Statement接口的特点：先进性字符串的拼接，再进行sql语句的编译
 *      优点：使用Statement可以进行sql语句的拼接
 *      缺点：因为拼接的存在，会导致SQL注入
 * java.sql.PreparedStatement接口的特点：先进行SQL语句的编译，然后再进行sql语句的传值
 *      优点：避免SQL注入
 *      缺点：没有办法进行sql语句的拼接，只能给sql语句传值
 *
 */
public class JDBCTest05 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String id = sc.nextLine();
        System.out.print("请输入密码：");
        String userPwd = sc.nextLine();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();

            // 3、获取预编译的数据库操作对象
            // ? 是占位符，一个 ? 只能接收一个“值/数据”，占位符两边不能有单引号
            String sql = "select id, password from user where id = ? and password = ?";
            stmt = conn.prepareStatement(sql); // 此时会发送sql给DBMS，进行sql语句的编译
            // 给占位符传值（jdbc所有下标都是从1开始）
            stmt.setString(1, id);
            stmt.setString(2, userPwd);

            // 4、执行SQL
            rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("登录成功");
            } else {
                System.out.println("登录失败");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 6、关闭资源
            JDBCUtil.closeResource(conn, stmt, rs);
        }
    }

}
