package com.revplay.test;

import com.revplay.model.PlayList;
import com.revplay.service.IPlayListService;
import com.revplay.service.PlayListServiceImpl;

import java.util.List;

public class PlayListTest {

    public static void main(String[] args) {

        IPlayListService service = new PlayListServiceImpl();

        System.out.println("------ PLAYLIST MODULE TEST ------");

        // ---------------- CREATE PLAYLIST ----------------
        PlayList newPlaylist = new PlayList(2, "Chill Hits", "Relaxing songs", "public");
        boolean created = service.createPlaylist(newPlaylist);
        System.out.println("Playlist created: " + created);
        System.out.println("Generated Playlist ID: " + newPlaylist.getPlaylistId());
//
//        // ---------------- UPDATE PLAYLIST ----------------
//        newPlaylist.setName("Chill Vibes Updated");
//        newPlaylist.setDescription("Relaxing songs updated");
//        newPlaylist.setPrivacyStatus("private");
//        boolean updated = service.updatePlaylist(newPlaylist);
//        System.out.println("Playlist updated: " + updated);
//
//        // ---------------- ADD SONGS ----------------
//        boolean added1 = service.addSong(newPlaylist.getPlaylistId(), 101); // 101 = song_id
//        boolean added2 = service.addSong(newPlaylist.getPlaylistId(), 102); // 102 = song_id
//        System.out.println("Songs added: " + added1 + ", " + added2);
//
//        // ---------------- REMOVE SONG ----------------
//        boolean removed = service.removeSong(newPlaylist.getPlaylistId(), 101);
//        System.out.println("Song removed: " + removed);
//
//        // ---------------- VIEW PUBLIC PLAYLISTS ----------------
//        List<PlayList> publicPlaylists = service.getPublicPlaylists();
//        System.out.println("Public Playlists:");
//        if (publicPlaylists.isEmpty()) {
//            System.out.println("No public playlists found.");
//        } else {
//            for (PlayList pl : publicPlaylists) {
//                System.out.println(
//                        pl.getPlaylistId() + " | " + pl.getName() + " | " +
//                                pl.getDescription() + " | " + pl.getPrivacyStatus()
//                );
//            }
//        }
//
//        // ---------------- DELETE PLAYLIST ----------------
//        boolean deleted = service.deletePlaylist(newPlaylist.getPlaylistId());
//        System.out.println("Playlist deleted: " + deleted);
//
//        System.out.println("------ TEST COMPLETED ------");
    }
}
