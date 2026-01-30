package com.revplay.service;

import com.revplay.dao.FakeAlbumDao;
import com.revplay.model.Album;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class AlbumServiceTest {

    private AlbumServiceImpl albumService;
    private FakeAlbumDao fakeAlbumDao;

    @BeforeEach
    public void setup() {
        albumService = new AlbumServiceImpl();
        fakeAlbumDao = new FakeAlbumDao();
        albumService.setAlbumDao(fakeAlbumDao);
    }

    @Test
    public void testCreateAlbum() {
        Album a = new Album();
        a.setTitle("New Album");
        a.setArtistId(5);

        boolean result = albumService.createAlbum(a);
        assertTrue(result);
        assertEquals(1, fakeAlbumDao.getAllAlbums().size());
        assertEquals("New Album", fakeAlbumDao.getAllAlbums().get(0).getTitle());
    }

    @Test
    public void testGetAllAlbums() {
        Album a1 = new Album();
        a1.setTitle("A1");
        Album a2 = new Album();
        a2.setTitle("A2");
        fakeAlbumDao.createAlbum(a1);
        fakeAlbumDao.createAlbum(a2);

        List<Album> albums = albumService.getAllAlbums();
        assertEquals(2, albums.size());
    }
}
