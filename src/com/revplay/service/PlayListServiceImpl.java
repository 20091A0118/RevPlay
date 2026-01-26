package com.revplay.service;

import com.revplay.dao.IPlayListDAO;
import com.revplay.dao.PlayListDAOImpl;
import com.revplay.model.PlayList;
import com.revplay.model.PlayListSong;
import java.util.List;

import java.time.LocalDateTime;

public class PlayListServiceImpl implements IPlayListService {

    private final IPlayListDAO dao = new PlayListDAOImpl();

    @Override
    public boolean createPlaylist(PlayList playlist) {
        return dao.createPlaylist(playlist) > 0;
    }


    @Override
    public boolean updatePlaylist(PlayList playlist) {
        return dao.updatePlaylist(playlist);
    }

    @Override
    public boolean deletePlaylist(int playlistId) {
        return dao.deletePlaylist(playlistId);
    }

    @Override
    public PlayList getPlaylistById(int playlistId) {
        return dao.getPlaylistById(playlistId);
    }

    @Override
    public boolean addSong(int playlistId, int songId) {
        return dao.addSongToPlaylistSong(playlistId, songId);
    }

    @Override
    public boolean removeSong(int playlistId, int songId) {
        return dao.removeSongFromPlaylist(playlistId, songId);
    }

    @Override
    public PlayListSong getSongByIds(int playlistId, int songId) {
        return dao.getSongFromPlaylistSong(playlistId, songId);
    }

    @Override
    public boolean updateSong(int playlistId, int oldSongId, int newSongId) {
        return dao.updateSongInPlaylist(playlistId, oldSongId, newSongId);
    }

    @Override
    public List<PlayListSong> getSongsByPlaylistId(int playlistId) {
        return dao.getAllSongsByPlaylistId(playlistId);
    }
    @Override
    public List<PlayList> getAllPlaylists() {
        return dao.getAllPlaylists();
    }

    @Override
    public List<PlayList> getAllPublicPlaylists() {
        return dao.getAllPublicPlaylists();
    }


}
