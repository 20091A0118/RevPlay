package com.revplay.dao;

import com.revplay.model.Song;
import java.util.List;

public interface ISongDao {

    void addSong(Song song);

    List<Song> getSongsByAlbum(int albumId);

    void updatePlayCount(int songId);
}


