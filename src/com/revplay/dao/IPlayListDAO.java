package com.revplay.dao;

import com.revplay.model.PlayList;
import com.revplay.model.PlayListSong;

import java.util.List;

public interface IPlayListDAO {

    int createPlaylist(PlayList playlist);

    PlayList getPlaylistById(int playlistId);

    boolean addSongToPlaylistSong(int playlistId, int songId);


 boolean removeSongFromPlaylist(int playlistId, int songId);

    boolean updatePlaylist(PlayList playlist);

    boolean deletePlaylist(int playlistId);

//    List<PlayList> getAllPlayLists();

    PlayListSong getSongFromPlaylistSong(int playlistId, int songId);

    boolean updateSongInPlaylist(int playlistId, int oldSongId, int newSongId);

    List<PlayListSong> getAllSongsByPlaylistId(int playlistId);

    List<PlayList> getAllPlaylists();

    List<PlayList> getAllPublicPlaylists();

}
