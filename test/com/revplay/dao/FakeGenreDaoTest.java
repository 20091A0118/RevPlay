package com.revplay.dao;

import com.revplay.model.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FakeGenreDaoTest {

    private FakeGenreDao dao;

    @BeforeEach
    public void setup() {
        dao = new FakeGenreDao();
    }

    @Test
    public void testAddGenre() {
        boolean result = dao.addGenre("Pop");
        assertTrue(result);
        assertEquals(1, dao.getAllGenres().size());
    }

    @Test
    public void testGetGenreByName() {
        dao.addGenre("Rock");
        Genre g = dao.getGenreByName("Rock");
        assertNotNull(g);
        assertEquals("Rock", g.getGenreName());
    }

    @Test
    public void testAddDuplicateGenre() {
        dao.addGenre("Jazz");
        boolean result = dao.addGenre("Jazz");
        assertFalse(result, "Should not add duplicate genre");
    }
}
