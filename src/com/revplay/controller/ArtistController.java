package com.revplay.controller;

import com.revplay.model.Album;
import com.revplay.model.ArtistAccount;
import com.revplay.model.Song;
import com.revplay.service.ArtistService;

import java.util.List;
import java.util.Scanner;

public class ArtistController {

    private Scanner sc = new Scanner(System.in);
    private ArtistService service = new ArtistService();

    public void start() {

        while (true) {
            System.out.println("\n===========================");
            System.out.println("      ARTIST MODULE");
            System.out.println("===========================");
            System.out.println("1. Register Artist");
            System.out.println("2. Login Artist");
            System.out.println("3. Back");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                register();
            } else if (choice == 2) {
                login();
            } else if (choice == 3) {
                return;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    private void register() {

        ArtistAccount a = new ArtistAccount();

        System.out.print("Enter Stage Name: ");
        a.setStageName(sc.nextLine());

        System.out.print("Enter Email: ");
        a.setEmail(sc.nextLine());

        System.out.print("Enter Password: ");
        a.setPasswordHash(sc.nextLine()); // storing plain password for now

        System.out.print("Enter Bio: ");
        a.setBio(sc.nextLine());

        System.out.print("Enter Genre: ");
        a.setGenre(sc.nextLine());

        System.out.print("Enter Instagram Link: ");
        a.setInstagramLink(sc.nextLine());

        System.out.print("Enter YouTube Link: ");
        a.setYoutubeLink(sc.nextLine());

        System.out.print("Enter Spotify Link: ");
        a.setSpotifyLink(sc.nextLine());

        System.out.print("Enter Status (ACTIVE/INACTIVE): ");
        a.setStatus(sc.nextLine());

        boolean ok = service.registerArtist(a);

        if (ok) System.out.println("Artist Registered Successfully ✅");
        else System.out.println("Artist Registration Failed ❌");
    }

    private void login() {

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        ArtistAccount artist = service.loginArtist(email, password);

        if (artist == null) {
            System.out.println("Login Failed ❌");
            return;
        }

        System.out.println("Login Successful ✅ Welcome " + artist.getStageName());
        artistDashboard(artist);
    }

    private void artistDashboard(ArtistAccount artist) {

        while (true) {
            System.out.println("\n===========================");
            System.out.println("     ARTIST DASHBOARD");
            System.out.println("===========================");
            System.out.println("1. View Profile");
            System.out.println("2. Update Profile");
            System.out.println("3. Create Album");
            System.out.println("4. View My Albums");
            System.out.println("5. Upload Song");
            System.out.println("6. View My Songs");
            System.out.println("7. Update Song");
            System.out.println("8. Delete Song");
            System.out.println("9. Update Album");
            System.out.println("10. Delete Album");
            System.out.println("11. View Stats");
            System.out.println("12. Logout");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    viewProfile(artist);
                    break;
                case 2:
                    updateProfile(artist);
                    break;
                case 3:
                    createAlbum(artist);
                    break;
                case 4:
                    viewAlbums(artist);
                    break;
                case 5:
                    uploadSong(artist);
                    break;
                case 6:
                    viewSongs(artist);
                    break;
                case 7:
                    updateSong(artist);
                    break;
                case 8:
                    deleteSong(artist);
                    break;
                case 9:
                    updateAlbum(artist);
                    break;
                case 10:
                    deleteAlbum(artist);
                    break;
                case 11:
                    service.viewStats(artist.getArtistId());
                    break;
                case 12:
                    System.out.println("Logout successful ✅");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void viewProfile(ArtistAccount artist) {

        ArtistAccount a = service.viewProfile(artist.getArtistId());

        if (a != null) {
            System.out.println("\n----- PROFILE -----");
            System.out.println("ID: " + a.getArtistId());
            System.out.println("Stage Name: " + a.getStageName());
            System.out.println("Email: " + a.getEmail());
            System.out.println("Bio: " + a.getBio());
            System.out.println("Genre: " + a.getGenre());
            System.out.println("Instagram: " + a.getInstagramLink());
            System.out.println("YouTube: " + a.getYoutubeLink());
            System.out.println("Spotify: " + a.getSpotifyLink());
            System.out.println("Status: " + a.getStatus());
        }
    }

    private void updateProfile(ArtistAccount artist) {

        System.out.print("Enter new Bio: ");
        String bio = sc.nextLine();

        System.out.print("Enter new Genre: ");
        String genre = sc.nextLine();

        System.out.print("Enter new Instagram Link: ");
        String instagram = sc.nextLine();

        System.out.print("Enter new YouTube Link: ");
        String youtube = sc.nextLine();

        System.out.print("Enter new Spotify Link: ");
        String spotify = sc.nextLine();

        System.out.print("Enter new Status (ACTIVE/INACTIVE): ");
        String status = sc.nextLine();

        boolean ok = service.updateProfile(artist.getArtistId(), bio, genre, instagram, youtube, spotify, status);

        if (ok) System.out.println("Profile Updated ✅");
        else System.out.println("Profile Update Failed ❌");
    }

    private void createAlbum(ArtistAccount artist) {

        Album al = new Album();
        al.setArtistId(artist.getArtistId());

        System.out.print("Enter Album Title: ");
        al.setTitle(sc.nextLine());

        System.out.print("Enter Album Genre: ");
        al.setGenre(sc.nextLine());

        System.out.print("Enter Release Date (YYYY-MM-DD): ");
        al.setReleaseDate(sc.nextLine());

        boolean ok = service.createAlbum(al);

        if (ok) System.out.println("Album Created ✅");
        else System.out.println("Album Creation Failed ❌");
    }

    private void viewAlbums(ArtistAccount artist) {

        List<Album> albums = service.viewAlbums(artist.getArtistId());

        System.out.println("\n----- MY ALBUMS -----");

        if (albums.isEmpty()) {
            System.out.println("No albums found!");
            return;
        }

        for (Album a : albums) {
            System.out.println(a);
        }
    }

    private void uploadSong(ArtistAccount artist) {

        Song s = new Song();
        s.setArtistId(artist.getArtistId());

        System.out.print("Enter Song Title: ");
        s.setTitle(sc.nextLine());

        System.out.print("Enter Song Genre: ");
        s.setGenre(sc.nextLine());

        System.out.print("Enter Duration in Seconds: ");
        s.setDurationSec(sc.nextInt());
        sc.nextLine();

        System.out.print("Enter Release Date (YYYY-MM-DD): ");
        s.setReleaseDate(sc.nextLine());

        System.out.print("Enter Album ID (0 if no album): ");
        int albumId = sc.nextInt();
        sc.nextLine();

        if (albumId == 0) s.setAlbumId(null);
        else s.setAlbumId(albumId);

        boolean ok = service.uploadSong(s);

        if (ok) System.out.println("Song Uploaded ✅");
        else System.out.println("Song Upload Failed ❌");
    }

    private void viewSongs(ArtistAccount artist) {

        List<Song> songs = service.viewSongs(artist.getArtistId());

        System.out.println("\n----- MY SONGS -----");

        if (songs.isEmpty()) {
            System.out.println("No songs found!");
            return;
        }

        for (Song s : songs) {
            System.out.println(s);
        }
    }

    private void updateSong(ArtistAccount artist) {

        Song s = new Song();
        s.setArtistId(artist.getArtistId());

        System.out.print("Enter Song ID to update: ");
        s.setSongId(sc.nextInt());
        sc.nextLine();

        System.out.print("Enter New Title: ");
        s.setTitle(sc.nextLine());

        System.out.print("Enter New Genre: ");
        s.setGenre(sc.nextLine());

        System.out.print("Enter New Duration (seconds): ");
        s.setDurationSec(sc.nextInt());
        sc.nextLine();

        System.out.print("Enter New Release Date (YYYY-MM-DD): ");
        s.setReleaseDate(sc.nextLine());

        boolean ok = service.updateSong(s);

        if (ok) System.out.println("Song Updated ✅");
        else System.out.println("Song Update Failed ❌");
    }

    private void deleteSong(ArtistAccount artist) {

        System.out.print("Enter Song ID to delete: ");
        int songId = sc.nextInt();
        sc.nextLine();

        boolean ok = service.deleteSong(songId, artist.getArtistId());

        if (ok) System.out.println("Song Deleted ✅");
        else System.out.println("Song Delete Failed ❌");
    }

    private void updateAlbum(ArtistAccount artist) {

        Album al = new Album();
        al.setArtistId(artist.getArtistId());

        System.out.print("Enter Album ID to update: ");
        al.setAlbumId(sc.nextInt());
        sc.nextLine();

        System.out.print("Enter New Album Title: ");
        al.setTitle(sc.nextLine());

        System.out.print("Enter New Genre: ");
        al.setGenre(sc.nextLine());

        System.out.print("Enter New Release Date (YYYY-MM-DD): ");
        al.setReleaseDate(sc.nextLine());

        boolean ok = service.updateAlbum(al);

        if (ok) System.out.println("Album Updated ✅");
        else System.out.println("Album Update Failed ❌");
    }

    private void deleteAlbum(ArtistAccount artist) {

        System.out.print("Enter Album ID to delete: ");
        int albumId = sc.nextInt();
        sc.nextLine();

        boolean ok = service.deleteAlbum(albumId, artist.getArtistId());

        if (ok) System.out.println("Album Deleted ✅");
        else System.out.println("Album Delete Failed ❌");
    }
}
