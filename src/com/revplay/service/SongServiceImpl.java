package com.revplay.service;

import com.revplay.dao.ISongDao;
import com.revplay.dao.SongDaoImpl;
import com.revplay.model.Song;

import java.util.List;

public class SongServiceImpl implements ISongService {

    private ISongDao songDao = new SongDaoImpl();

    @Override
    public void addSong(Song song) {
        songDao.addSong(song);
    }

    @Override
    public List<Song> viewSongsByAlbum(int albumId) {
        return songDao.getSongsByAlbum(albumId);
    }

    @Override
    public void playSong(int songId) {
        songDao.updatePlayCount(songId);
    }
}
