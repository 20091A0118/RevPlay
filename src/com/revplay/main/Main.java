package com.revplay.main;

import java.util.Scanner;
import com.revplay.controller.ArtistController;
import com.revplay.controller.UserAccountController;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===========================");
            System.out.println("     RevPlay Main Menu");
            System.out.println("===========================");
            System.out.println("1. User Module");
            System.out.println("2. Artist Module");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    new UserAccountController().start();
                    break;

                case 2:
                    new ArtistController().start();
                    break;

                case 3:
                    System.out.println("Thank you for using RevPlay!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
