package com.revplay.dao;

import com.revplay.model.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FakePlaylistDaoTest {

    private FakePlaylistDao dao;

    @BeforeEach
    public void setup() {
        dao = new FakePlaylistDao();
    }

    @Test
    public void testCreatePlaylist() {
        Playlist p = new Playlist();
        p.setPlaylistId(1);
        p.setName("My Jam");
        p.setUserId(10);

        boolean result = dao.createPlaylist(p);
        assertTrue(result);
        assertEquals(1, dao.getPlaylistsByUserId(10).size());
    }

    @Test
    public void testDeletePlaylist() {
        Playlist p = new Playlist();
        p.setPlaylistId(5);
        p.setUserId(10);
        dao.createPlaylist(p);

        boolean result = dao.deletePlaylist(5);
        assertTrue(result);
        assertEquals(0, dao.getPlaylistsByUserId(10).size());
    }
}
