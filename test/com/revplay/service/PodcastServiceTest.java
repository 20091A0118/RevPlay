package com.revplay.service;

import com.revplay.dao.FakePodcastDao;
import com.revplay.model.Podcast;
import com.revplay.model.PodcastEpisode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

public class PodcastServiceTest {

    private PodcastServiceImpl podcastService;
    private FakePodcastDao fakeDao;

    @BeforeEach
    public void setup() {
        podcastService = new PodcastServiceImpl();
        fakeDao = new FakePodcastDao();
        podcastService.setPodcastDao(fakeDao);
    }

    @Test
    public void testCreatePodcast_Success() {
        Podcast p = new Podcast();
        p.setTitle("Tech Talk");
        p.setHostName("John Doe");
        p.setCategory("Technology");
        p.setCreatedAt(LocalDateTime.now());

        boolean result = podcastService.createPodcast(p);
        assertTrue(result, "Podcast creation should succeed");
        assertEquals(1, fakeDao.getAllPodcasts().size());
        assertEquals("Tech Talk", fakeDao.getAllPodcasts().get(0).getTitle());
    }

    @Test
    public void testAddEpisode_Success() {
        Podcast p = new Podcast();
        p.setPodcastId(1);
        fakeDao.createPodcast(p);

        PodcastEpisode ep = new PodcastEpisode();
        ep.setPodcastId(1);
        ep.setTitle("Episode 1");
        ep.setDurationSeconds(3600);
        ep.setCreatedAt(LocalDateTime.now());

        boolean result = podcastService.addEpisode(ep);
        assertTrue(result, "Adding episode should succeed");
        assertEquals(1, fakeDao.getEpisodesByPodcastId(1).size());
    }

    @Test
    public void testPlayEpisode_Success() {
        PodcastEpisode ep = new PodcastEpisode();
        ep.setEpisodeId(10);
        ep.setTitle("Cool Episode");
        fakeDao.addEpisode(ep);

        boolean result = podcastService.playEpisode(10);
        assertTrue(result, "Playing episode should succeed");
        assertEquals(1, ep.getPlayCount(), "Play count should increment");
    }

    @Test
    public void testPlayEpisode_NotFound() {
        boolean result = podcastService.playEpisode(999);
        assertFalse(result, "Playing non-existent episode should fail");
    }

    @Test
    public void testSearchPodcast() {
        Podcast p1 = new Podcast();
        p1.setTitle("Java Programming");
        Podcast p2 = new Podcast();
        p2.setTitle("Cooking 101");
        fakeDao.createPodcast(p1);
        fakeDao.createPodcast(p2);

        List<Podcast> results = podcastService.searchPodcastByTitle("Java");
        assertEquals(1, results.size());
        assertEquals("Java Programming", results.get(0).getTitle());
    }
}
