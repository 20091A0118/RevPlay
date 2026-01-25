package com.revplay.dao;

import com.revplay.model.Album;
import java.util.List;

public interface IAlbumDAO {
    boolean createAlbum(Album album);
    List<Album> getAlbumsByArtist(int artistId);
    boolean updateAlbum(Album album);
    boolean deleteAlbum(int albumId, int artistId);
}
