package com.revplay.auth.main;

import com.revplay.auth.controller.AuthController;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("=== Welcome to RevPlay ===");
        new AuthController().startAuth();
    }
}
