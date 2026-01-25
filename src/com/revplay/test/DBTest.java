package com.revplay.test;

import com.revplay.util.JDBCUtil;

public class DBTest {
    public static void main(String[] args) {
        System.out.println(JDBCUtil.getConnection());
    }
}
