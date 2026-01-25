package com.revplay.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class JDBCUtil {

    private static String url;
    private static String username;
    private static String password;

    static {
        try (InputStream is = JDBCUtil.class.getResourceAsStream("/db.properties")) {

            Properties props = new Properties();
            props.load(is);

            url = props.getProperty("db.url");
            username = props.getProperty("db.username");
            password = props.getProperty("db.password");

            System.out.println("✅ DB properties loaded");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JDBCUtil() {}

    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
