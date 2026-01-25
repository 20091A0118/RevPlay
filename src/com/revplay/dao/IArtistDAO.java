package com.revplay.dao;

import com.revplay.model.ArtistAccount;

public interface IArtistDAO {

    boolean registerArtist(ArtistAccount artist);

    ArtistAccount loginArtist(String email, String passwordHash);

    ArtistAccount getArtistById(int artistId);

    boolean updateArtistProfile(int artistId, String bio, String genre,
                                String instagramLink, String youtubeLink,
                                String spotifyLink, String status);
}
