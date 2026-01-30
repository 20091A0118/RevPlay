package com.revplay.dao;

import com.revplay.model.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FakeSongDaoTest {

    private FakeSongDao dao;

    @BeforeEach
    public void setup() {
        dao = new FakeSongDao();
    }

    @Test
    public void testAddSong() {
        Song s = new Song();
        s.setTitle("New Song");

        boolean result = dao.addSong(s);
        assertTrue(result);
        assertEquals(1, dao.getAllSongs().size());
    }

    @Test
    public void testGetSongById() {
        Song s = new Song();
        s.setSongId(123);
        s.setTitle("Specific Song");
        dao.addSong(s);

        Song retrieved = dao.getSongById(123);
        assertNotNull(retrieved);
        assertEquals("Specific Song", retrieved.getTitle());
    }
}
