package club.kwcoder.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * JDBC默认情况下是自动提交，执行一次DML语句就提交一次
 *
 * 在实际开发中，必须将JDBC自动提交机制关闭掉，改成手动提交
 *
 */
public class JDBCTest06 {

    public static void main(String[] args) {

        ResourceBundle bundle = ResourceBundle.getBundle("resources/db");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // 1、加载驱动
            Class.forName(driver);

            // 2、获取数据库连接对象
            conn = DriverManager.getConnection(url, user, password);
            // 开启事务，将自动提交机制关闭掉
            conn.setAutoCommit(false);

            String sql = "update back set money = ? where account = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, 1000);
            stmt.setString(2, "A");
            stmt.executeUpdate();

            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, 1000);
            stmt.setString(2, "B");
            stmt.executeUpdate();

            conn.commit();

        } catch (ClassNotFoundException | SQLException e) {
            if (null != conn) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
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
