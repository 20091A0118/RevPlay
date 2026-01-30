package com.revplay.dao;

import com.revplay.model.Song;
import java.util.ArrayList;
import java.util.List;

public class FakeSongDao implements ISongDao {

    private List<Song> songs = new ArrayList<>();

    @Override
    public boolean addSong(Song song) {
        songs.add(song);
        return true;
    }

    @Override
    public Song getSongById(int songId) {
        return songs.stream().filter(s -> s.getSongId() == songId).findFirst().orElse(null);
    }

    public List<Song> getAllSongs() {
        return songs;
    }

    @Override
    public boolean incrementPlayCount(int songId) {
        Song s = getSongById(songId);
        if (s != null) {
            s.setPlayCount(s.getPlayCount() + 1);
            return true;
        }
        return false;
    }

    // Unused or simple return for test
    @Override
    public List<Song> getSongsByArtistId(int artistId) {
        return new ArrayList<>();
    }

    @Override
    public List<Song> getSongsByAlbumId(int albumId) {
        return new ArrayList<>();
    }

    @Override
    public List<Song> searchSongs(String keyword) {
        return new ArrayList<>();
    }

    @Override
    public boolean deleteSong(int songId) {
        return false;
    }
}
