package com.revplay.dao;

import com.revplay.model.Podcast;
import com.revplay.model.PodcastEpisode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

public class FakePodcastDaoTest {

    private FakePodcastDao dao;

    @BeforeEach
    public void setup() {
        dao = new FakePodcastDao();
    }

    @Test
    public void testCreatePodcast() {
        Podcast p = new Podcast();
        p.setTitle("Tech News");
        p.setCreatedAt(LocalDateTime.now()); // Ensure required fields are set if needed

        boolean result = dao.createPodcast(p);
        assertTrue(result);
        assertEquals(1, dao.getAllPodcasts().size());
    }

    @Test
    public void testAddEpisode() {
        PodcastEpisode ep = new PodcastEpisode();
        ep.setTitle("Episode 1");
        ep.setPodcastId(1);

        boolean result = dao.addEpisode(ep);
        assertTrue(result);
        assertEquals(1, dao.getEpisodesByPodcastId(1).size());
    }
}
