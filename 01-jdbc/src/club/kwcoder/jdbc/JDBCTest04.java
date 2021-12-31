package club.kwcoder.jdbc;

import club.kwcoder.jdbc.utils.JDBCUtil;

import java.sql.*;
import java.util.Scanner;

public class JDBCTest04 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String id = sc.nextLine();
        System.out.print("请输入密码：");
        String userPwd = sc.nextLine();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();

            // 3、获取数据库操作对象
            stmt = JDBCUtil.getStatement(conn);

            // 4、执行SQL
            String sql = "select id, password from user where id = '" + id + "' and password = '" + userPwd + "'";
            System.out.println(sql);
            rs = stmt.executeQuery(sql);

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
