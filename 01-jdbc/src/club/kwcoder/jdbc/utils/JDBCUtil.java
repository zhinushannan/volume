package club.kwcoder.jdbc.utils;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCUtil {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // 资源绑定器
        ResourceBundle bundle = ResourceBundle.getBundle("resources/db");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Class.forName(driver);

        return DriverManager.getConnection(url, user, password);
    }

    public static Statement getStatement(Connection conn) throws SQLException {
        Statement stmt = null;
        if (null != conn) {
            stmt = conn.createStatement();
        }
        return stmt;
    }

    public static void closeResource(Connection conn, Statement stmt, ResultSet rs) {
        if (null != conn) {
            try {
                conn.close();
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
        if (null != rs) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
