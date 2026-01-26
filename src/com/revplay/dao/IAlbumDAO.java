package com.revplay.dao;

import com.revplay.model.Album;

import java.util.List;

public interface IAlbumDAO {

    int createAlbum(Album album); // ✅ returns album_id

    List<Album> getAlbumsByArtist(int artistId);

    boolean updateAlbum(Album album);

    boolean deleteAlbum(int albumId, int artistId);
}
