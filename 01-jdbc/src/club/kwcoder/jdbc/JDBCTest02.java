package club.kwcoder.jdbc;

/**
 * 使用类加载器注册驱动
 * 优点：根据完整的类名寻找类进行加载注册，可以将完整类名的字符串写在配置文件中
 */
public class JDBCTest02 {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
