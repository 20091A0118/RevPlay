package com.revplay.dao;

import com.revplay.model.Album;
import java.util.ArrayList;
import java.util.List;

public class FakeAlbumDao implements IAlbumDao {

    private List<Album> albums = new ArrayList<>();

    @Override
    public boolean createAlbum(Album album) {
        albums.add(album);
        return true;
    }

    @Override
    public List<Album> getAllAlbums() {
        return albums;
    }

    @Override
    public Album getAlbumById(int albumId) {
        return albums.stream().filter(a -> a.getAlbumId() == albumId).findFirst().orElse(null);
    }

    // Unused
    @Override
    public List<Album> getAlbumsByArtistId(int artistId) {
        return new ArrayList<>();
    }

    @Override
    public boolean deleteAlbum(int albumId) {
        return false;
    }
}
