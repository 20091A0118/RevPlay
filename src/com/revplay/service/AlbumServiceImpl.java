package com.revplay.service;

import com.revplay.dao.AlbumDaoImpl;
import com.revplay.dao.IAlbumDao;
import com.revplay.model.Album;

import java.util.List;

public class AlbumServiceImpl implements IAlbumService {

    private IAlbumDao albumDao = new AlbumDaoImpl();

    @Override
    public void createAlbum(Album album) {
        albumDao.addAlbum(album);
    }

    @Override
    public List<Album> viewAlbumsByArtist(int artistId) {
        return albumDao.getAlbumsByArtist(artistId);
    }
}

