package com.revplay.dao;

import com.revplay.model.Artist;

public interface IArtistDAO {

    boolean registerArtist(Artist artist);

    Artist loginArtist(String email, String password);

    Artist getArtistById(int artistId);

    boolean updateArtistProfile(int artistId, String bio, String genre, String instagram, String youtube);
}
