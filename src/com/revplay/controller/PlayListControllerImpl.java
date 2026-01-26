package com.revplay.controller;

import com.revplay.model.PlayList;
import com.revplay.model.PlayListSong;
import com.revplay.service.IPlayListService;
import com.revplay.service.PlayListServiceImpl;

import java.util.List;

public class PlayListControllerImpl implements IPlayListController {

    private final IPlayListService service = new PlayListServiceImpl();

    // Playlist operations
    @Override
    public boolean createPlaylist(PlayList playlist) {
        return service.createPlaylist(playlist);
    }

    @Override
    public boolean updatePlaylist(PlayList playlist) {
        return service.updatePlaylist(playlist);
    }

    @Override
    public boolean deletePlaylist(int playlistId) {
        return service.deletePlaylist(playlistId);
    }

    @Override
    public PlayList getPlaylistById(int playlistId) {
        return service.getPlaylistById(playlistId);
    }

    @Override
    public List<PlayList> getAllPlaylists() {
        return service.getAllPlaylists();
    }

    // PlaylistSong operations
    @Override
    public boolean addSongToPlaylist(int playlistId, int songId) {
        return service.addSong(playlistId, songId);
    }

    @Override
    public boolean removeSongFromPlaylist(int playlistId, int songId) {
        return service.removeSong(playlistId, songId);
    }

    @Override
    public PlayListSong getSongFromPlaylist(int playlistId, int songId) {
        return service.getSongByIds(playlistId, songId);
    }

    @Override
    public boolean updateSongInPlaylist(int playlistId, int oldSongId, int newSongId) {
        return service.updateSong(playlistId, oldSongId, newSongId);
    }

    @Override
    public List<PlayListSong> getSongsByPlaylistId(int playlistId) {
        return service.getSongsByPlaylistId(playlistId);
    }

    @Override
    public List<PlayList> getAllPublicPlaylists() {
        return service.getAllPublicPlaylists();
    }

}
