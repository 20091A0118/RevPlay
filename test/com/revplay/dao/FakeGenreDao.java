package com.revplay.dao;

import com.revplay.model.Genre;
import java.util.ArrayList;
import java.util.List;

public class FakeGenreDao implements IGenreDao {

    private List<Genre> genres = new ArrayList<>();
    private int genreIdCounter = 1;

    @Override
    public List<Genre> getAllGenres() {
        return new ArrayList<>(genres);
    }

    @Override
    public Genre getGenreById(int id) {
        return genres.stream()
                .filter(g -> g.getGenreId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Genre getGenreByName(String name) {
        return genres.stream()
                .filter(g -> g.getGenreName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean addGenre(String genreName) {
        if (getGenreByName(genreName) != null) {
            return false;
        }
        Genre g = new Genre(genreIdCounter++, genreName);
        genres.add(g);
        return true;
    }
}
