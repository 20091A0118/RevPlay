package com.revplay.dao;

import com.revplay.model.ArtistAccount;
import java.util.ArrayList;
import java.util.List;

public class FakeArtistDao implements IArtistDao {

    private List<ArtistAccount> artists = new ArrayList<>();

    @Override
    public boolean registerArtist(ArtistAccount artist) {
        if (getArtistByEmail(artist.getEmail()) != null)
            return false;
        artists.add(artist);
        return true;
    }

    @Override
    public ArtistAccount getArtistByEmail(String email) {
        return artists.stream()
                .filter(a -> a.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    // Unused
    @Override
    public ArtistAccount getArtistById(int id) {
        return null;
    }

    @Override
    public boolean updateArtistProfile(ArtistAccount artist) {
        return false;
    }

    @Override
    public List<ArtistAccount> getAllArtists() {
        return artists;
    }

    @Override
    public boolean deleteArtist(int artistId) {
        return false;
    }
}
