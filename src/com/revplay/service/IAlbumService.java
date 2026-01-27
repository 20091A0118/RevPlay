package com.revplay.service;

import com.revplay.model.Album;
import java.util.List;

public interface IAlbumService {

    void createAlbum(Album album);

    List<Album> viewAlbumsByArtist(int artistId);
}

