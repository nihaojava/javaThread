package chapter10;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by zhangzhao on 2018/8/28.
 *
 */
public class MyTest27 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc://localhost:3306/mytestdb", "username", "password");
    }
}
