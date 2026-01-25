package com.revplay.test;

import com.revplay.dao.*;
import java.util.Scanner;

public class UserLibraryTest {

    public static void main(String[] args) {

        SearchDaoImpl search = new SearchDaoImpl();
        FavoriteDaoImpl fav = new FavoriteDaoImpl();
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Search Song");
        System.out.println("2. Search Artist");
        System.out.println("3. Search Album");
        System.out.println("4. Search Podcast");
        System.out.println("5. Browse by Genre");
        System.out.println("6. Browse by Artist");
        System.out.println("7. Browse by Album");
        System.out.println("8. View Favorites");

        int ch = sc.nextInt();

        switch (ch) {
            case 1 -> System.out.println(search.searchSongs(""));
            case 2 -> System.out.println(search.searchArtists(""));
            case 3 -> System.out.println(search.searchAlbums(""));
            case 4 -> System.out.println(search.searchPodcasts(""));
            case 5 -> System.out.println(search.browseByGenre(1));
            case 6 -> System.out.println(search.browseByArtist(1));
            case 7 -> System.out.println(search.browseByAlbum(1));
            case 8 -> System.out.println(fav.getFavoriteSongs(1));
        }
    }
}
