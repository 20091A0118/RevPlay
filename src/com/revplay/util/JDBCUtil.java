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

        try (InputStream is = JDBCUtil.class

                .getClassLoader()

                .getResourceAsStream("com/revplay/util/db.properties")) {

            Properties props = new Properties();

            props.load(is);

            url = props.getProperty("db.url");

            username = props.getProperty("db.username");

            password = props.getProperty("db.password");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    private JDBCUtil() {}

    public static Connection getConnection() {

        Connection con = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return con;

    }

}


