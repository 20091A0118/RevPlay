package com.revplay.test;

import com.revplay.controller.*;
import com.revplay.dao.SearchDaoImpl;
import com.revplay.model.Song;

import java.util.List;
import java.util.Scanner;

public class UserLibraryTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Controllers
        FavoriteController favoriteController = new FavoriteController();
        ListeningHistoryController historyController = new ListeningHistoryController();
        SearchDaoImpl search = new SearchDaoImpl();

        int USER_ID = 1; // existing user in DB

        while (true) {
            System.out.println("\n========= RevPlay =========");
            System.out.println("1. Search Songs");
            System.out.println("2. Search Artists");
            System.out.println("3. Search Albums");
            System.out.println("4. Browse by Genre");
            System.out.println("5. Browse by Artist");
            System.out.println("6. Browse by Album");
            System.out.println("7. View Favorites");
            System.out.println("8. Add Favorite");
            System.out.println("9. Remove Favorite");
            System.out.println("10. Play Song");
            System.out.println("11. Pause Song");
            System.out.println("12. Skip Song");
            System.out.println("13. Recently Played");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1 -> {
                    System.out.print("Keyword (Enter for all): ");
                    String kw = sc.nextLine();
                    List<Song> songs = search.searchSongs(kw);
                    if (songs.isEmpty()) System.out.println("No songs found.");
                    else songs.forEach(System.out::println);
                }

                case 2 -> search.searchArtists("").forEach(System.out::println);

                case 3 -> search.searchAlbums("").forEach(System.out::println);

                case 4 -> search.browseByGenre(1).forEach(System.out::println);

                case 5 -> search.browseByArtist(1).forEach(System.out::println);

                case 6 -> search.browseByAlbum(1).forEach(System.out::println);

                case 7 -> favoriteController.view(USER_ID);

                case 8 -> {
                    System.out.print("Song id: ");
                    favoriteController.add(USER_ID, sc.nextInt());
                }

                case 9 -> {
                    System.out.print("Song id: ");
                    favoriteController.remove(USER_ID, sc.nextInt());
                }

                case 10 -> {
                    System.out.print("Song id: ");
                    historyController.play(USER_ID, sc.nextInt());
                }

                case 11 -> {
                    System.out.print("Song id: ");
                    historyController.pause(USER_ID, sc.nextInt());
                }

                case 12 -> {
                    System.out.print("Song id: ");
                    historyController.skip(USER_ID, sc.nextInt());
                }

                case 13 -> historyController.recent(USER_ID);

                case 0 -> {
                    System.out.println("Thank you for using RevPlay 👋");
                    return;
                }

                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
