package com.revplay.service;

import com.revplay.dao.GenreDaoImpl;
import com.revplay.dao.IGenreDao;
import com.revplay.model.Genre;

import java.util.List;

public class GenreServiceImpl implements IGenreService {

    private IGenreDao genreDao = new GenreDaoImpl();

    @Override
    public void addGenre(Genre genre) {
        genreDao.addGenre(genre);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreDao.getAllGenres();
    }
}

