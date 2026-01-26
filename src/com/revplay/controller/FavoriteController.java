package com.revplay.controller;

import com.revplay.model.Song;
import com.revplay.service.*;

import java.util.List;

public class FavoriteController {

    private IFavoriteService service = new FavoriteServiceImpl();

    public void add(int userId, int songId) {
        if (service.addFavorite(userId, songId))
            System.out.println("✅ Added to favorites");
        else
            System.out.println("⚠️ Already exists or invalid");
    }

    public void remove(int userId, int songId) {
        if (service.removeFavorite(userId, songId))
            System.out.println("🗑️ Removed from favorites");
        else
            System.out.println("❌ Not found in favorites");
    }

    public void view(int userId) {
        List<Song> list = service.viewFavorites(userId);
        if (list.isEmpty()) System.out.println("No favorites yet.");
        else list.forEach(System.out::println);
    }
}
