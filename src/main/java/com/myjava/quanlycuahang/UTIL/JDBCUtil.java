package com.myjava.quanlycuahang.UTIL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author truon
 */
public class JDBCUtil {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/doanjava2";
    static final String USER = "root";
    static final String PASS = "";

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch(Exception e) {
            System.out.println("Error when connecting to database");
            e.printStackTrace();
            throw new SQLException("Error when connecting to database");
        }
        return conn;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
