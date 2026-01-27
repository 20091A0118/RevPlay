package com.revplay.dao;

import com.revplay.model.Genre;
import java.util.List;

public interface IGenreDao {
    void addGenre(Genre genre);
    void updateGenre(Genre genre);
    void deleteGenre(int genreId);
    List<Genre> getAllGenres();
}

