package com.revplay.dao;

import com.revplay.model.*;
import java.util.List;

public interface ISearchDao {
    List<Song> searchSongs(String keyword);
    List<ArtistAccount> searchArtists(String keyword);
    List<Album> searchAlbums(String keyword);

    List<Song> browseByGenre(int genreId);
    List<Song> browseByArtist(int artistId);
    List<Song> browseByAlbum(int albumId);
}
