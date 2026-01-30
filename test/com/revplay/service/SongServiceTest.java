package com.revplay.service;

import com.revplay.dao.FakeSongDao;
import com.revplay.dao.IListeningHistoryDao;
import com.revplay.model.ListeningHistory;
import com.revplay.model.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class SongServiceTest {

    private SongServiceImpl songService;
    private FakeSongDao fakeSongDao;

    // Simple inline fake for history since it has 1 method used
    private class FakeHistoryDao implements IListeningHistoryDao {
        boolean historyAdded = false;

        @Override
        public boolean addHistory(ListeningHistory history) {
            historyAdded = true;
            return true;
        }

        @Override
        public List<Song> getUserHistory(int userId) {
            return new ArrayList<>();
        }
    }

    private FakeHistoryDao fakeHistoryDao;

    @BeforeEach
    public void setup() {
        songService = new SongServiceImpl();
        fakeSongDao = new FakeSongDao();
        fakeHistoryDao = new FakeHistoryDao();

        songService.setSongDao(fakeSongDao);
        songService.setHistoryDao(fakeHistoryDao);
    }

    @Test
    public void testUploadSong() {
        Song s = new Song();
        s.setTitle("Test Song");

        boolean result = songService.uploadSong(s);
        assertTrue(result);
        assertEquals(1, fakeSongDao.getAllSongs().size());
        assertNotNull(s.getReleaseDate()); // Should set default date
        assertEquals("ACTIVE", s.getIsActive());
    }

    @Test
    public void testPlaySong() {
        Song s = new Song();
        s.setSongId(10);
        s.setTitle("Hit Song");
        fakeSongDao.addSong(s);

        boolean result = songService.playSong(10, 500); // song 10, user 500
        assertTrue(result);
        assertTrue(fakeHistoryDao.historyAdded);
        assertEquals(1, s.getPlayCount());
    }
}
