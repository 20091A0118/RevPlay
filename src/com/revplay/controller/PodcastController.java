package com.revplay.controller;

import com.revplay.model.Podcast;
import com.revplay.service.PodcastServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class PodcastController {

    private PodcastServiceImpl service = new PodcastServiceImpl();
    private Scanner sc = new Scanner(System.in);

    public void start() {
        int choice;

        do {
            System.out.println("""
                ===== PODCAST MODULE =====
                1. Create Podcast
                2. Update Podcast
                3. Delete Podcast
                4. View Podcasts
                0. Exit
                """);

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> create();
                case 2 -> update();
                case 3 -> delete();
                case 4 -> view();
                case 0 -> System.out.println("Exiting Podcast Module...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private void create() {
        System.out.print("Title: ");
        String title = sc.nextLine();

        System.out.print("Host: ");
        String host = sc.nextLine();

        System.out.print("Category: ");
        String category = sc.nextLine();

        System.out.print("Description: ");
        String desc = sc.nextLine();

        Podcast p = new Podcast(0, title, host, category, desc, LocalDateTime.now());
        service.createPodcast(p);
        System.out.println("Podcast Created ✅");
    }

    private void update() {
        System.out.print("Podcast ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("New Title: ");
        String title = sc.nextLine();

        System.out.print("New Host: ");
        String host = sc.nextLine();

        System.out.print("New Category: ");
        String category = sc.nextLine();

        System.out.print("New Description: ");
        String desc = sc.nextLine();

        Podcast p = new Podcast(id, title, host, category, desc, null);
        service.updatePodcast(p);
        System.out.println("Podcast Updated ✅");
    }

    private void delete() {
        System.out.print("Podcast ID: ");
        int id = sc.nextInt();
        service.deletePodcast(id);
        System.out.println("Podcast Deleted ✅");
    }

    private void view() {
        List<Podcast> list = service.viewAllPodcasts();
        list.forEach(p ->
                System.out.println(
                        p.getPodcastId() + " | " +
                                p.getTitle() + " | " +
                                p.getHostName()
                )
        );
    }
}
