package com.revplay.auth.controller;

import com.revplay.auth.model.User;
import com.revplay.auth.service.UserService;
import com.revplay.auth.service.UserServiceImpl;

import java.util.Scanner;

public class AuthController {

    private static UserService userService = new UserServiceImpl();

    public static void startAuth() {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== REVPLAY AUTH MENU ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Change Password");
            System.out.println("4. Forgot Password");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> register(sc);
                case 2 -> login(sc);
                case 3 -> changePassword(sc);
                case 4 -> forgotPassword(sc);
                case 5 -> {
                    running = false;
                    System.out.println("Exiting Auth Module...");
                }
                default -> System.out.println("Invalid choice!");
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

        User user = new User(0, name, email, password, question, answer);

        boolean success = userService.registerUser(user);
        System.out.println(success ? "Registration Successful!" : "Email already exists!");
    }

    private static void login(Scanner sc) {
        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = userService.login(email, password);
        System.out.println(user != null ? "Login Successful! Welcome " + user.getUsername() : "Invalid Credentials!");
    }

    private static void changePassword(Scanner sc) {
        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Old Password: ");
        String oldPass = sc.nextLine();

        System.out.print("New Password: ");
        String newPass = sc.nextLine();

        boolean success = userService.changePassword(email, oldPass, newPass);
        System.out.println(success ? "Password changed!" : "Password change failed!");
    }

    private static void forgotPassword(Scanner sc) {
        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Security Answer: ");
        String answer = sc.nextLine();

        String password = userService.forgotPassword(email, answer);
        System.out.println(password != null ? "Your password is: " + password : "Invalid details!");
    }
}
