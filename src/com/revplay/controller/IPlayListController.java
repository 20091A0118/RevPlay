package com.revplay.controller;

import com.revplay.model.PlayList;
import com.revplay.model.PlayListSong;

import java.util.List;

public interface IPlayListController {

    // Playlist operations
    boolean createPlaylist(PlayList playlist);
    boolean updatePlaylist(PlayList playlist);
    boolean deletePlaylist(int playlistId);
    PlayList getPlaylistById(int playlistId);
    List<PlayList> getAllPlaylists();

    // PlaylistSong operations
    boolean addSongToPlaylist(int playlistId, int songId);
    boolean removeSongFromPlaylist(int playlistId, int songId);
    PlayListSong getSongFromPlaylist(int playlistId, int songId);
    boolean updateSongInPlaylist(int playlistId, int oldSongId, int newSongId);
    List<PlayListSong> getSongsByPlaylistId(int playlistId);
    List<PlayList> getAllPublicPlaylists();

}
