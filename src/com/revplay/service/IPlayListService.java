package com.revplay.service;

import com.revplay.model.PlayList;
import com.revplay.model.PlayListSong;
import java.util.List;

public interface IPlayListService {

    boolean createPlaylist(PlayList playlist);


    boolean updatePlaylist(PlayList playlist);

    boolean deletePlaylist(int playlistId);


    PlayList getPlaylistById(int playlistId);

    boolean addSong(int playlistId, int songId);

    boolean removeSong(int playlistId, int songId);

     PlayListSong getSongByIds(int playlistId, int songId);

    boolean updateSong(int playlistId, int oldSongId, int newSongId);

    List<PlayListSong> getSongsByPlaylistId(int playlistId);

    List<PlayList> getAllPlaylists();

    List<PlayList> getAllPublicPlaylists();


}
