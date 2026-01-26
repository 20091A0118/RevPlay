//
//
//package com.revplay.test;
//
//import com.revplay.model.PlayList;
//import com.revplay.service.IPlayListService;
//import com.revplay.service.PlayListServiceImpl;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//public class PlayListTest {
//
//    public static void main(String[] args) {
//
//        IPlayListService service = new PlayListServiceImpl();
//
//        // ---------------- CREATE PLAYLIST ----------------
//        PlayList playlist = new PlayList();
//        playlist.setUserId(2); // example user id
//        playlist.setName("Mashup");
//        playlist.setDescription("Hindi songs");
//        playlist.setPrivacyStatus("private"); // public/private
//        playlist.setCreatedAt(LocalDateTime.now());
//        playlist.setUpdatedAt(LocalDateTime.now());
//
//        boolean created = service.createPlaylist(playlist);
//        System.out.println("Playlist created: " + created + ", Playlist ID: " + playlist.getPlaylistId());
//
//        // ---------------- UPDATE PLAYLIST ----------------
//        playlist.setPlaylistId(1);
//        playlist.setName("My Updated Favorite Songs");
//        playlist.setDescription("Updated description");
//        playlist.setPrivacyStatus("public");
//        boolean updated = service.updatePlaylist(playlist);
//        System.out.println("Playlist updated: " + updated);
//
//     //    ---------------- ADD SONGS ----------------
//        int playlistId = 1; // hard-coded playlist ID
//        int playlistIdd = service.getPlayListIdByName("Mashup");
//
//        boolean songAdded1 = service.addSong(playlistIdd,101); // songId = 101
//        boolean songAdded2 = service.addSong(playlistIdd, 102); // songId = 102
//        System.out.println("Song 101 added: " + songAdded1);
//        System.out.println("Song 102 added: " + songAdded2);
//        System.out.println("Generated Playlist ID: " + playlist.getPlaylistId());
//
//
////        System.out.println("Test execution completed successfully");
//
// //---------------- REMOVE SONG ----------------
//        int pid=1;
//        boolean songRemoved = service.removeSong(pid, 101);
//        System.out.println("Song 101 removed: " + songRemoved);
//
//        // ---------------- FETCH PUBLIC PLAYLISTS ----------------
//        List<PlayList> publicPlaylists = service.getPublicPlaylists();
//        System.out.println("Public Playlists:");
//        for (PlayList p : publicPlaylists) {
//            System.out.println("ID: " + p.getPlaylistId() + ", Name: " + p.getName() +
//                    ", Description: " + p.getDescription() + ", Privacy: " + p.getPrivacyStatus());
//        }
//
//        // ---------------- DELETE PLAYLIST ----------------
//
//        boolean deleted = service.deletePlaylist(2);
//        System.out.println("Playlist deleted: " + deleted+"playlistid:");
//
//    }
//}
