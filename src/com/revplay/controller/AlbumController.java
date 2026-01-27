package com.revplay.controller;

import com.revplay.model.Album;
import com.revplay.service.AlbumServiceImpl;
import com.revplay.service.IAlbumService;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class AlbumController {

    private IAlbumService albumService = new AlbumServiceImpl();
    private Scanner sc = new Scanner(System.in);

    public void addAlbum() {

        System.out.print("Enter album name: ");
        String name = sc.nextLine();

        System.out.print("Enter artist ID: ");
        int artistId = sc.nextInt();

        System.out.print("Enter release date (yyyy-mm-dd): ");
        Date releaseDate = Date.valueOf(sc.next());

        Album album = new Album(name, artistId, releaseDate);
        albumService.createAlbum(album);
    }

    public void viewAlbumsByArtist() {

        System.out.print("Enter artist ID: ");
        int artistId = sc.nextInt();

        List<Album> albums = albumService.viewAlbumsByArtist(artistId);

        if (albums.isEmpty()) {
            System.out.println("No albums found");
        } else {
            for (Album a : albums) {
                System.out.println(
                        a.getAlbumId() + " | " +
                                a.getAlbumName() + " | " +
                                a.getReleaseDate()
                );
            }
        }
    }
}
