package com.revplay.main;

import com.revplay.controller.IPlayListController;
import com.revplay.controller.PlayListControllerImpl;
import com.revplay.model.PlayList;
import com.revplay.model.PlayListSong;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        IPlayListController controller = new PlayListControllerImpl();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n====== PLAYLIST MANAGEMENT ======");
            System.out.println("1. Create Playlist");
            System.out.println("2. Update Playlist");
            System.out.println("3. Delete Playlist");
            System.out.println("4. Get Playlist By Id");
            System.out.println("5. Add song to playlist");
            System.out.println("6. Remove song from playlist");
            System.out.println("7. Get song from playlist");
            System.out.println("8. Update song in playlist");
            System.out.println("9. Get all songs by playlist id");
            System.out.println("10. Get all playlists");
            System.out.println("11.get all public playlists");
            System.out.println("12. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1 -> {
                    PlayList p = new PlayList();

                    System.out.print("Enter User ID: ");
                    p.setUserId(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Enter Playlist Name: ");
                    p.setName(sc.nextLine());

                    System.out.print("Enter Description: ");
                    p.setDescription(sc.nextLine());

                    System.out.print("Enter Privacy (PUBLIC/PRIVATE): ");
                    p.setPrivacyStatus(sc.nextLine());

                    boolean created = controller.createPlaylist(p);
                    System.out.println(created ? " Playlist Created" : "Creation Failed");
                }

                case 2 -> {
                    PlayList p = new PlayList();

                    System.out.print("Enter Playlist ID: ");
                    p.setPlaylistId(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    p.setName(sc.nextLine());

                    System.out.print("Enter New Description: ");
                    p.setDescription(sc.nextLine());

                    System.out.print("Enter Privacy: ");
                    p.setPrivacyStatus(sc.nextLine());

                    System.out.println(
                            controller.updatePlaylist(p)
                                    ? " Playlist Updated"
                                    : "Update Failed"
                    );
                }

                case 3 -> {
                    System.out.print("Enter Playlist ID: ");
                    int pid = sc.nextInt();

                    System.out.println(
                            controller.deletePlaylist(pid)
                                    ? "Playlist Deleted"
                                    : "Delete Failed"
                    );
                }

                case 4 -> {
                    System.out.print("Enter Playlist ID: ");
                    int pid = sc.nextInt();
                    sc.nextLine();

                    PlayList p = controller.getPlaylistById(pid);
                    if (p != null) {
                        System.out.println("✅ Playlist Found:");
                        System.out.println("ID: " + p.getPlaylistId());
                        System.out.println("User ID: " + p.getUserId());
                        System.out.println("Name: " + p.getName());
                        System.out.println("Description: " + p.getDescription());
                        System.out.println("Privacy: " + p.getPrivacyStatus());
                        System.out.println("Created At: " + p.getCreatedAt());
                        System.out.println("Updated At: " + p.getUpdatedAt());
                    } else {
                        System.out.println("Playlist Not Found");
                    }
                       }

                case 5 -> {
                    System.out.print("Playlist ID: ");
                    int pid = sc.nextInt();

                    System.out.print("Song ID: ");
                    int sid = sc.nextInt();

                    System.out.println(
                            controller.addSongToPlaylist(pid, sid)
                                    ? "Song Added"
                                    : "Failed"
                    );
                }

                case 6 -> {
                    System.out.print("Playlist ID: ");
                    int pid = sc.nextInt();

                    System.out.print("Song ID: ");
                    int sid = sc.nextInt();

                    System.out.println(
                            controller.removeSongFromPlaylist(pid, sid)
                                    ? " Song Removed"
                                    : " Failed"
                    );
                }
                case 7-> {
                    System.out.print("Enter Playlist ID: ");
                    int pid = sc.nextInt();

                    System.out.print("Enter Song ID: ");
                    int sid = sc.nextInt();
                    sc.nextLine();

                    PlayListSong ps =controller.getSongFromPlaylist(pid,sid);
                    if (ps != null) {
                        System.out.println("Song Found in Playlist:");
                        System.out.println("Playlist ID: " + ps.getPlaylistId());
                        System.out.println("Song ID: " + ps.getSongId());
                        System.out.println("Added At: " + ps.getAddedAt());
                    } else {
                        System.out.println("Song Not Found in Playlist");
                    }
                }



                case 8 -> {
                    System.out.print("Playlist ID: ");
                    int pid = sc.nextInt();

                    System.out.print("Old Song ID: ");
                    int oldSid = sc.nextInt();

                    System.out.print("New Song ID: ");
                    int newSid = sc.nextInt();

                    System.out.println(
                            controller.updateSongInPlaylist(pid, oldSid, newSid)
                                    ? " Song Updated"
                                    : "Update Failed"
                    );
                }

                case 9 -> { // Get all songs in a playlist
                    System.out.print("Enter Playlist ID: ");
                    int pid = sc.nextInt();
                    sc.nextLine();

                    List<PlayListSong> songs = controller.getSongsByPlaylistId(pid);
                    if (songs.isEmpty()) {
                        System.out.println(" No songs found in this playlist");
                    } else {
                        System.out.println("Songs in Playlist " + pid + ":");
                        for (PlayListSong s : songs) {
                            System.out.println("Song ID: " + s.getSongId() + " | Added At: " + s.getAddedAt());
                        }
                    }
                }
                case 10 -> { // Get all playlists
                    System.out.println(" All Playlists:");
                    controller.getAllPlaylists().forEach(p ->
                            System.out.println(
                                    p.getPlaylistId() + " | " +
                                            p.getUserId() + " | " +
                                            p.getName() + " | " +
                                            p.getDescription() + " | " +
                                            p.getPrivacyStatus() + " | " +
                                            p.getCreatedAt() + " | " +
                                            p.getUpdatedAt()
                            )
                    );
                }
//
                case 11 -> {
                    System.out.println("Public Playlists:");
                    controller.getAllPublicPlaylists().forEach(p ->
                            System.out.println(
                                    p.getPlaylistId() + " | " +
                                            p.getUserId() + " | " +
                                            p.getName() + " | " +
                                            p.getDescription() + " | " +
                                            p.getPrivacyStatus() + " | " +
                                            p.getCreatedAt() + " | " +
                                            p.getUpdatedAt()
                            )
                    );
                }

                case 12 -> {
                    System.out.println(" Exiting");
                    sc.close();
                    return;
                }

                default -> System.out.println(" Invalid Option");
            }
        }
    }
}

