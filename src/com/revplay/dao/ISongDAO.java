package com.revplay.dao;

import com.revplay.model.Song;
import java.util.List;

public interface ISongDAO {

    boolean uploadSong(Song song);

    List<Song> getSongsByArtist(int artistId);

    List<Song> getSongsByAlbum(int albumId);

    List<Song> searchSongsByTitle(String keyword);

    boolean updateSong(Song song);

    boolean deleteSong(int songId, int artistId);
}
