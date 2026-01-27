package com.revplay.controller;

import com.revplay.model.Song;
import com.revplay.service.ISongService;
import com.revplay.service.SongServiceImpl;

import java.util.List;
import java.util.Scanner;

public class SongController {

    private ISongService songService = new SongServiceImpl();
    private Scanner sc = new Scanner(System.in);

    public void addSong() {

        System.out.print("Enter song title: ");
        String title = sc.nextLine();

        System.out.print("Enter album ID: ");
        int albumId = sc.nextInt();

        System.out.print("Enter genre ID: ");
        int genreId = sc.nextInt();

        System.out.print("Enter duration (seconds): ");
        int duration = sc.nextInt();

        Song song = new Song(title, albumId, genreId, duration);
        songService.addSong(song);
    }

    public void viewSongsByAlbum() {

        System.out.print("Enter album ID: ");
        int albumId = sc.nextInt();

        List<Song> songs = songService.viewSongsByAlbum(albumId);

        if (songs.isEmpty()) {
            System.out.println("No songs found");
        } else {
            for (Song s : songs) {
                System.out.println(
                        s.getSongId() + " | " +
                                s.getTitle() + " | Duration: " +
                                s.getDuration() + " | Plays: " +
                                s.getPlayCount()
                );
            }
        }
    }

    public void playSong() {

        System.out.print("Enter song ID to play: ");
        int songId = sc.nextInt();
        songService.playSong(songId);
        System.out.println("Playing song...");
    }
}
