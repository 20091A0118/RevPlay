package com.revplay.dao;

import com.revplay.model.Song;
import java.util.List;

public interface ISongDAO {
    boolean uploadSong(Song song);
    List<Song> getSongsByArtist(int artistId);
    boolean updateSong(Song song);
    boolean deleteSong(int songId, int artistId);
}
