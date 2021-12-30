package club.kwcoder.jdbc;


import java.sql.*;

public class JDBCTest01 {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1、注册驱动
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            // 2、获取数据库连接对象
            String url = "jdbc:mysql://localhost:3306/jdbc_study";
            String user = "root";
            String password = "09140727";
            conn = DriverManager.getConnection(url, user, password);

            // 3、获取数据库操作对象
            stmt = conn.createStatement();

            // 4、执行SQL语句
            stmt.executeUpdate("delete from employee");

            String e_no1 = "xxx";
            String e_name1 = "小明";
            double e_salary1 = 2000.5;

            String e_no2 = "yyy";
            String e_name2 = "小红";
            double e_salary2 = 5000.3;

            String insertSQL1 = "insert into employee (e_no, e_name, e_salary) values ('" + e_no1 + "', '" + e_name1 + "', " + e_salary1 + ")";
            String insertSQL2 = "insert into employee (e_no, e_name, e_salary) values ('" + e_no2 + "', '" + e_name2 + "', " + e_salary2 + ")";
            System.out.println(insertSQL1);
            int insertCount1 = stmt.executeUpdate(insertSQL1);
            int insertCount2 = stmt.executeUpdate(insertSQL2);
            System.out.println(insertCount1);
            System.out.println(insertCount2);


            // 5、处理查询结果集
            String selectSQL = "select * from employee";
            rs = stmt.executeQuery(selectSQL);
            while (rs.next()) {
                String e_no = rs.getString("e_no");
                String e_name = rs.getString("e_name");
                Double e_salary = rs.getDouble("e_salary");
                System.out.printf("e_no=%S, e_name=%S, e_salary=%f\n", e_no, e_name, e_salary);
            }


            String deleteSQL = "delete from employee";
            int deleteCount = stmt.executeUpdate(deleteSQL);
            System.out.println(deleteCount);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 6、关闭资源
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
