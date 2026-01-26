package com.revplay.service;

import com.revplay.model.Song;
import java.util.List;

public interface IFavoriteService {
    boolean addFavorite(int userId, int songId);
    boolean removeFavorite(int userId, int songId);
    List<Song> viewFavorites(int userId);
}
