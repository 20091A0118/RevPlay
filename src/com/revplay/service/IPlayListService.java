package com.revplay.service;

import com.revplay.model.PlayList;

import java.util.List;

public interface IPlayListService {

    // Playlist operations
    boolean createPlaylist(PlayList playlist);

    boolean updatePlaylist(PlayList playlist);

    boolean deletePlaylist(int playlistId);

    List<PlayList> getPublicPlaylists();

    // Playlist-Song operations
    boolean addSong(int playlistId, int songId);

    boolean removeSong(int playlistId, int songId);
}
