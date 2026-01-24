package com.revplay.auth.main;

import com.revplay.auth.util.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class TestDB {
    public static void main(String[] args) {
        try {
            Connection con = DBConnection.getConnection();
            System.out.println("Database Connected Successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to connect to DB!");
            e.printStackTrace();
        }
    }
}