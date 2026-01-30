package com.revplay.service;

import com.revplay.dao.FakeGenreDao;
import com.revplay.model.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class GenreServiceTest {

    private GenreServiceImpl genreService;
    private FakeGenreDao fakeDao;

    @BeforeEach
    public void setup() {
        genreService = new GenreServiceImpl();
        fakeDao = new FakeGenreDao();
        genreService.setGenreDao(fakeDao);
    }

    @Test
    public void testGetOrCreateGenre_Existing() {
        fakeDao.addGenre("Rock");

        Genre g = genreService.getOrCreateGenre("Rock");
        assertNotNull(g);
        assertEquals("Rock", g.getGenreName());
        assertEquals(1, fakeDao.getAllGenres().size()); // Should not add duplicate
    }

    @Test
    public void testGetOrCreateGenre_New() {
        Genre g = genreService.getOrCreateGenre("Jazz");
        assertNotNull(g);
        assertEquals("Jazz", g.getGenreName());
        assertEquals(1, fakeDao.getAllGenres().size());
    }

    @Test
    public void testGetAllGenres() {
        fakeDao.addGenre("Pop");
        fakeDao.addGenre("Classical");

        List<Genre> genres = genreService.getAllGenres();
        assertEquals(2, genres.size());
    }

    @Test
    public void testGetOrCreateGenre_CaseInsensitive() {
        fakeDao.addGenre("HipHop");

        Genre g = genreService.getOrCreateGenre("hiphop");
        // Note: Implementation usually depends on DAO. FakeDAO we made is
        // case-insensitive for getGenreByName

        assertNotNull(g);
        assertEquals("HipHop", g.getGenreName());
    }
}
