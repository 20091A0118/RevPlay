package com.revplay.test;

import com.revplay.dao.*;
import com.revplay.model.*;

import java.util.*;

public class UserLibraryTest {

    public static void main(String[] args) {

        SearchDaoImpl search = new SearchDaoImpl();
        FavoriteDaoImpl fav = new FavoriteDaoImpl();
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Search Songs");
        System.out.println("2. Search Artists");
        System.out.println("3. Search Albums");
        System.out.println("4. Search Podcasts");
        System.out.println("5. Browse by Genre");
        System.out.println("6. Browse by Artist");
        System.out.println("7. Browse by Album");
        System.out.println("8. View Favorites");

        int ch = sc.nextInt();

        switch (ch) {

            case 1 -> {
                System.out.println("🎵 Songs:");
                search.searchSongs("").forEach(
                        s -> System.out.println(s.getSongId() + " - " + s.getTitle())
                );
            }

            case 2 -> {
                System.out.println("🎤 Artists:");
                search.searchArtists("").forEach(
                        a -> System.out.println(a.getArtistId() + " - " + a.getStageName())
                );
            }

            case 3 -> {
                System.out.println("💿 Albums:");
                search.searchAlbums("").forEach(
                        a -> System.out.println(a.getAlbumId() + " - " + a.getTitle())
                );
            }

            case 4 -> {
                System.out.println("🎙️ Podcasts:");
                search.searchPodcasts("").forEach(
                        p -> System.out.println(p.getPodcastId() + " - " + p.getTitle())
                );
            }

            case 5 -> {
                System.out.print("Enter genre id: ");
                int id = sc.nextInt();
                search.browseByGenre(id).forEach(
                        s -> System.out.println(s.getSongId() + " - " + s.getTitle())
                );
            }

            case 6 -> {
                System.out.print("Enter artist id: ");
                int id = sc.nextInt();
                search.browseByArtist(id).forEach(
                        s -> System.out.println(s.getSongId() + " - " + s.getTitle())
                );
            }

            case 7 -> {
                System.out.print("Enter album id: ");
                int id = sc.nextInt();
                search.browseByAlbum(id).forEach(
                        s -> System.out.println(s.getSongId() + " - " + s.getTitle())
                );
            }

            case 8 -> {
                System.out.println("❤️ Favorites:");
                fav.getFavoriteSongs(1).forEach(
                        s -> System.out.println(s.getSongId() + " - " + s.getTitle())
                );
            }

            default -> System.out.println("Invalid option");
        }
    }
}
