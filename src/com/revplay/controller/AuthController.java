package com.revplay.controller;

import com.revplay.model.UserAccount;
import com.revplay.service.UserService;
import com.revplay.service.UserServiceImpl;

import java.util.Scanner;

public class AuthController {

    private static final UserService userService = new UserServiceImpl();

    public static void startAuth() {
        Scanner sc = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("\n=== REVPLAY AUTH MENU ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Change Password");
            System.out.println("4. Forgot Password");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> register(sc);
                case 2 -> login(sc);
                case 3 -> changePassword(sc);
                case 4 -> forgotPassword(sc);
                case 5 -> {
                    run = false;
                    System.out.println("Exiting Auth Module...");
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void register(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Security Question: ");
        String question = sc.nextLine();

        System.out.print("Security Answer: ");
        String answer = sc.nextLine();

        UserAccount user = new UserAccount(
                0,
                name,
                email,
                password,
                question,
                answer,
                "ACTIVE"
        );

        boolean success = userService.registerUser(user);
        System.out.println(success ? "Registered Successfully" : "User already exists");
    }

    private static void login(Scanner sc) {
        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        UserAccount user = userService.login(email, password);

        System.out.println(user != null
                ? "Login Successful! Welcome " + user.getFullName()
                : "Invalid Credentials");
    }

    private static void changePassword(Scanner sc) {
        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Old Password: ");
        String oldPass = sc.nextLine();

        System.out.print("New Password: ");
        String newPass = sc.nextLine();

        boolean success = userService.changePassword(email, oldPass, newPass);
        System.out.println(success ? "Password updated" : "Failed to update password");
    }

    private static void forgotPassword(Scanner sc) {
        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Security Answer: ");
        String answer = sc.nextLine();

        String pwd = userService.forgotPassword(email, answer);
        System.out.println(pwd != null ? "Your password: " + pwd : "Invalid details");
    }
}
