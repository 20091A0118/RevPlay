package com.revplay.service;

import com.revplay.dao.*;
import com.revplay.model.Song;
import java.util.List;

public class FavoriteServiceImpl implements IFavoriteService {

    private IFavoriteDao dao = new FavoriteDaoImpl();

    public boolean addFavorite(int userId, int songId) {
        return dao.addFavorite(userId, songId);
    }

    public boolean removeFavorite(int userId, int songId) {
        return dao.removeFavorite(userId, songId);
    }

    public List<Song> viewFavorites(int userId) {
        return dao.getFavorites(userId);
    }
}
