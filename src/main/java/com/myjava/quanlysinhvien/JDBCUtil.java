package com.myjava.quanlysinhvien;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author truon
 */
public class JDBCUtil {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/doanjava2";
    static final String USER = "root";
    static final String PASS = "123456";

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch(Exception e) {
            System.err.println("Loi khong the ket noi!!!");
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
                System.out.println("Disconnected database");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
