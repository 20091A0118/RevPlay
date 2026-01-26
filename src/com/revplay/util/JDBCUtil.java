package com.revplay.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCUtil {

    private static Connection connection;

    static {
        try {
            Properties props = new Properties();

            InputStream is = JDBCUtil.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties");

            if (is == null) {
                throw new RuntimeException("db.properties not found in src folder");
            }

            props.load(is);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.username");
            String pass = props.getProperty("db.password");
            String driver = props.getProperty("db.driver");

            Class.forName(driver);

            connection = DriverManager.getConnection(url, user, pass);

            System.out.println("✅ Database connected successfully (FreeSQL)");

        } catch (Exception e) {
            System.out.println("❌ Database connection failed");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
