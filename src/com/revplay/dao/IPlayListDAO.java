package com.revplay.dao;


import com.revplay.model.PlayList;
import com.revplay.model.PlayListSong;

import java.util.List;

public interface IPlayListDAO {

    // Playlist operations
    boolean createPlayList(PlayList playlist);
    boolean updatePlayList(PlayList playlist);
    boolean deletePlayList(int playlistId);
    List<PlayList> getPublicPlayLists();

    // Playlist-Song operations
    boolean addSongToPlaylist(PlayListSong psong);
    boolean removeSongFromPlaylist(int playlistId, int songId);
}
