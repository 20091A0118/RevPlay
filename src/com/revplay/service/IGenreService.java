package com.revplay.service;

import com.revplay.model.Genre;
import java.util.List;

public interface IGenreService {
    void addGenre(Genre genre);
    List<Genre> getAllGenres();
}

