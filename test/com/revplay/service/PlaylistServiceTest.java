package com.revplay.service;

import com.revplay.dao.FakePlaylistDao;
import com.revplay.model.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PlaylistServiceTest {

    private PlaylistServiceImpl playlistService;
    private FakePlaylistDao fakeDao;

    @BeforeEach
    public void setup() {
        playlistService = new PlaylistServiceImpl();
        fakeDao = new FakePlaylistDao();
        playlistService.setPlaylistDao(fakeDao);
    }

    @Test
    public void testCreatePlaylist() {
        Playlist p = new Playlist();
        p.setUserId(1);
        p.setName("My Favorites");

        boolean result = playlistService.createPlaylist(p);
        assertTrue(result);

        List<Playlist> userPlaylists = playlistService.getUserPlaylists(1);
        assertEquals(1, userPlaylists.size());
        assertEquals("My Favorites", userPlaylists.get(0).getName());
    }

    @Test
    public void testDeletePlaylist() {
        Playlist p = new Playlist();
        p.setPlaylistId(100);
        fakeDao.createPlaylist(p);

        boolean result = playlistService.deletePlaylist(100);
        assertTrue(result);
        assertEquals(0, fakeDao.getPlaylistsByUserId(0).size()); // checking internal state
    }
}
