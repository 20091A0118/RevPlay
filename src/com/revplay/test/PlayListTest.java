//package com.revplay.test;
//
//import com.revplay.model.PlayList;
//import com.revplay.model.PlayListSong;
//import com.revplay.service.PlayListServiceImpl;
//
//import java.time.LocalDateTime;
//
//public class PlayListTest {
//
//    public static void main(String[] args) {
//
//        PlayListServiceImpl service = new PlayListServiceImpl();
//
//        System.out.println("------ PLAYLIST MODULE TEST ------");
//
//        // 1️⃣ CREATE PLAYLIST
//        PlayList newPlaylist = new PlayList(5,"Dj","Fast beat","public");
////        boolean created = service.createPlaylist(newPlaylist);
////        System.out.println("Playlist created: " + created);
////        System.out.println("Generated Playlist ID: " + newPlaylist.getPlaylistId());
////
////        if (!created) {
////            System.out.println("Cannot proceed. Playlist creation failed.");
////            return;
////        }
//        //ADD SONGS TO PLAYLISTSONG
//        int playlistId = newPlaylist.getPlaylistId(); // get ID from database
//
//// Make sure song IDs exist in the song table!
//        PlayListSong song1 = new PlayListSong(playlistId, 101);
//        PlayListSong song2 = new PlayListSong(playlistId, 102);
//
//        boolean added1 = service.addSong(song1.getPlaylistId(), song1.getSongId());
//        boolean added2 = service.addSong(song2.getPlaylistId(), song2.getSongId());
//
//        System.out.println("Songs added: " + added1 + ", " + added2);
//
//        //️⃣ UPDATE PLAYLIST
////        newPlaylist.setPlaylistId(4);
////        newPlaylist.setName("Chilled");
////        newPlaylist.setDescription("Relaxed songs");
////        newPlaylist.setPrivacyStatus("public");
////
////
////        boolean updated = service.updatePlaylist(newPlaylist);
////        System.out.println("Playlist updated: " + updated +","+"playlist id:"+newPlaylist.getPlaylistId());
//
//        // ✅ DONE
//        System.out.println("------ TEST COMPLETED ------");
// }
//}

package com.revplay.test;

import com.revplay.model.PlayList;
import com.revplay.service.IPlayListService;
import com.revplay.service.PlayListServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

public class PlayListTest {

    public static void main(String[] args) {

        IPlayListService service = new PlayListServiceImpl();

        // ---------------- CREATE PLAYLIST ----------------
        PlayList playlist = new PlayList();
//        playlist.setUserId(2); // example user id
//        playlist.setName("Mashup");
//        playlist.setDescription("Hindi songs");
//        playlist.setPrivacyStatus("private"); // public/private
//        playlist.setCreatedAt(LocalDateTime.now());
//        playlist.setUpdatedAt(LocalDateTime.now());
//
//        boolean created = service.createPlaylist(playlist);
//        System.out.println("Playlist created: " + created + ", Playlist ID: " + playlist.getPlaylistId());

        // ---------------- UPDATE PLAYLIST ----------------
//        playlist.setPlaylistId(1);
//        playlist.setName("My Updated Favorite Songs");
//        playlist.setDescription("Updated description");
//        playlist.setPrivacyStatus("public");
//        boolean updated = service.updatePlaylist(playlist);
//        System.out.println("Playlist updated: " + updated);

     //    ---------------- ADD SONGS ----------------
//        int playlistId = 1; // hard-coded playlist ID
//        int playlistId = service.getPlayListIdByName("Mashup");
//
//        boolean songAdded1 = service.addSong(playlistId,101); // songId = 101
//        boolean songAdded2 = service.addSong(playlistId, 102); // songId = 102
//        System.out.println("Song 101 added: " + songAdded1);
//        System.out.println("Song 102 added: " + songAdded2);
//        System.out.println("Generated Playlist ID: " + playlist.getPlaylistId());
//
//
//        System.out.println("Test execution completed successfully");

 //---------------- REMOVE SONG ----------------
        int pid=1;
        boolean songRemoved = service.removeSong(pid, 101);
        System.out.println("Song 101 removed: " + songRemoved);

        // ---------------- FETCH PUBLIC PLAYLISTS ----------------
//        List<PlayList> publicPlaylists = service.getPublicPlaylists();
//        System.out.println("Public Playlists:");
//        for (PlayList p : publicPlaylists) {
//            System.out.println("ID: " + p.getPlaylistId() + ", Name: " + p.getName() +
//                    ", Description: " + p.getDescription() + ", Privacy: " + p.getPrivacyStatus());
//        }

        // ---------------- DELETE PLAYLIST ----------------
//
//        boolean deleted = service.deletePlaylist(2);
//        System.out.println("Playlist deleted: " + deleted+"playlistid:");

    }
}
