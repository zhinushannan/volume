package club.kwcoder.jdbc.utils;

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtil {

    // 工具类中的构造方法一般都是私有化的，用于防止被new对象
    // 因为工具类中的方法都是静态的，不需要new对象，直接用“类名.方法名”调用
    private DBUtil() {}

    // 类加载时绑定资源文件
    private static final ResourceBundle bundle = ResourceBundle.getBundle("resources/db");

    // 注册驱动
    static {
        try {
            Class.forName(bundle.getString("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection () throws SQLException {
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        return DriverManager.getConnection(url, user, password);
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
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
