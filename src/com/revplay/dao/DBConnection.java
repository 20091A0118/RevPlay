package com.revplay.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DB_URL = "jdbc:oracle:thin:@db.freesql.com:1521/23ai_34ui2";
    private static final String DB_USER = "CHINNUREDDI735_SCHEMA_9CYLZ";
    private static final String DB_PASS ="3O8X$2BL8OHGK8J1JTKIK6GuF71DHO" ;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}
