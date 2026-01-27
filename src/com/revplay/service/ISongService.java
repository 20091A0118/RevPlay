package com.revplay.service;

import com.revplay.model.Song;
import java.util.List;

public interface ISongService {

    void addSong(Song song);

    List<Song> viewSongsByAlbum(int albumId);

    void playSong(int songId);
}
