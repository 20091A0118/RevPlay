package com.revplay.dashboard;

import com.revplay.model.UserAccount;
import com.revplay.service.UserService;
import com.revplay.service.UserServiceImpl;

import java.util.Scanner;

public class DashboardController {

    private static final UserService userService = new UserServiceImpl();

    public static void start(UserAccount user) {
        Scanner sc = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("\n=== USER DASHBOARD ===");
            System.out.println("1. View Profile");
            System.out.println("2. Change Password");
            System.out.println("3. Logout");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> viewProfile(user);
                case 2 -> changePassword(user, sc);
                case 3 -> {
                    run = false;
                    System.out.println("Logged out successfully");
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void viewProfile(UserAccount user) {
        System.out.println("\n--- USER PROFILE ---");
        System.out.println("Name   : " + user.getFullName());
        System.out.println("Email  : " + user.getEmail());
        System.out.println("Status : " + user.getStatus());
    }

    private static void changePassword(UserAccount user, Scanner sc) {
        System.out.print("Old Password: ");
        String oldPass = sc.nextLine();

        System.out.print("New Password: ");
        String newPass = sc.nextLine();

        boolean success = userService.changePassword(
                user.getEmail(),
                oldPass,
                newPass
        );

        System.out.println(success ? "Password updated" : "Failed to update password");
    }
}