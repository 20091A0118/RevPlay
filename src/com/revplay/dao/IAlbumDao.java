package com.revplay.dao;

import com.revplay.model.Album;
import java.util.List;

public interface IAlbumDao {

    void addAlbum(Album album);

    List<Album> getAlbumsByArtist(int artistId);
}

