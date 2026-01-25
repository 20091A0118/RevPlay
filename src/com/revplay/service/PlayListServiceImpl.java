package com.revplay.service;

import com.revplay.dao.IPlayListDAO;
import com.revplay.dao.PlayListDAOImpl;
import com.revplay.model.PlayList;
import com.revplay.model.PlayListSong;

import java.time.LocalDateTime;
import java.util.List;

public class PlayListServiceImpl implements IPlayListService {

    private final IPlayListDAO dao = new PlayListDAOImpl();

    @Override
    public boolean createPlaylist(PlayList playlist) {
        // Set created and updated timestamps if not set
        if (playlist.getCreatedAt() == null) playlist.setCreatedAt(LocalDateTime.now());
        if (playlist.getUpdatedAt() == null) playlist.setUpdatedAt(LocalDateTime.now());
        return dao.createPlayList(playlist);
    }

    @Override
    public boolean updatePlaylist(PlayList playlist) {
        playlist.setUpdatedAt(LocalDateTime.now()); // always update timestamp
        // Ensure privacyStatus is not null
        if (playlist.getPrivacyStatus() == null || playlist.getPrivacyStatus().isEmpty()) {
            // Set a default value if null
            playlist.setPrivacyStatus("public");
        }
        return dao.updatePlayList(playlist);
    }

    @Override
    public boolean deletePlaylist(int playlistId) {
        return dao.deletePlayList(playlistId);
    }

    @Override
    public boolean addSong(int playlistId, int songId) {
        PlayListSong psong =
                new PlayListSong(playlistId, songId, LocalDateTime.now());

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

    @Override
    public int getPlayListIdByName(String name){
        return dao.getPlaylistIdByName(name);
    }
}
