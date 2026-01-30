package com.revplay.dao;

import com.revplay.model.Album;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FakeAlbumDaoTest {

    private FakeAlbumDao dao;

    @BeforeEach
    public void setup() {
        dao = new FakeAlbumDao();
    }

    @Test
    public void testCreateAlbum() {
        Album a = new Album();
        a.setTitle("Greatest Hits");

        boolean result = dao.createAlbum(a);
        assertTrue(result);
        assertEquals(1, dao.getAllAlbums().size());
    }

    @Test
    public void testGetAlbumById() {
        Album a = new Album();
        a.setAlbumId(55);
        a.setTitle("Test Album");
        dao.createAlbum(a);

        Album retrieved = dao.getAlbumById(55);
        assertNotNull(retrieved);
        assertEquals("Test Album", retrieved.getTitle());
    }
}
