package com.revplay.dao;

import com.revplay.model.Playlist;
import com.revplay.model.Song;
import java.util.ArrayList;
import java.util.List;

public class FakePlaylistDao implements IPlaylistDao {

    private List<Playlist> playlists = new ArrayList<>();
    private List<Integer> playlistSongs = new ArrayList<>(); // Simplification: just tracking song IDs for *some*
                                                             // playlist

    @Override
    public boolean createPlaylist(Playlist playlist) {
        playlists.add(playlist);
        return true;
    }

    @Override
    public boolean deletePlaylist(int playlistId) {
        return playlists.removeIf(p -> p.getPlaylistId() == playlistId);
    }

    @Override
    public List<Playlist> getPlaylistsByUserId(int userId) {
        List<Playlist> result = new ArrayList<>();
        for (Playlist p : playlists) {
            if (p.getUserId() == userId)
                result.add(p);
        }
        return result;
    }

    // Checking minimal implementation for service test
    @Override
    public boolean addSongToPlaylist(int playlistId, int songId) {
        playlistSongs.add(songId);
        return true;
    }

    @Override
    public boolean removeSongFromPlaylist(int playlistId, int songId) {
        return playlistSongs.remove((Integer) songId);
    }

    @Override
    public List<Song> getSongsInPlaylist(int playlistId) {
        return new ArrayList<>();
    }
}
