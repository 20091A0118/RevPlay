package com.revplay.service;

import com.revplay.dao.IPlayListDAO;
import com.revplay.dao.PlayListDAOImpl;
import com.revplay.model.PlayList;
import com.revplay.model.PlayListSong;

import java.util.List;

public class PlayListServiceImpl implements IPlayListService {

    private final IPlayListDAO dao = new PlayListDAOImpl();

    @Override
    public boolean createPlaylist(PlayList playlist) {
        return dao.createPlayList(playlist);
    }

    @Override
    public boolean updatePlaylist(PlayList playlist) {
        return dao.updatePlayList(playlist);
    }

    @Override
    public boolean deletePlaylist(int playlistId) {
        return dao.deletePlayList(playlistId);
    }

    @Override
    public boolean addSong(int playlistId, int songId) {
        PlayListSong psong = new PlayListSong(playlistId, songId);
        return dao.addSongToPlaylist(psong);
    }

    @Override
    public boolean removeSong(int playlistId, int songId) {
        return dao.removeSongFromPlaylist(playlistId, songId);
    }

    @Override
    public List<PlayList> getPublicPlaylists() {
        return dao.getPublicPlayLists();
    }
}
