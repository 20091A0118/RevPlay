package com.revplay.dao;

import com.revplay.model.Podcast;
import com.revplay.model.PodcastEpisode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FakePodcastDao implements IPodcastDao {

    private List<Podcast> podcasts = new ArrayList<>();
    private List<PodcastEpisode> episodes = new ArrayList<>();
    private int podcastIdCounter = 1;
    private int episodeIdCounter = 1;

    @Override
    public boolean createPodcast(Podcast podcast) {
        podcast.setPodcastId(podcastIdCounter++);
        podcasts.add(podcast);
        return true;
    }

    @Override
    public List<Podcast> getAllPodcasts() {
        return new ArrayList<>(podcasts);
    }

    @Override
    public Podcast getPodcastById(int id) {
        return podcasts.stream()
                .filter(p -> p.getPodcastId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updatePodcast(Podcast podcast) {
        Podcast existing = getPodcastById(podcast.getPodcastId());
        if (existing != null) {
            existing.setTitle(podcast.getTitle());
            existing.setDescription(podcast.getDescription());
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePodcast(int id) {
        return podcasts.removeIf(p -> p.getPodcastId() == id);
    }

    @Override
    public List<Podcast> searchPodcastByTitle(String title) {
        return podcasts.stream()
                .filter(p -> p.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean addEpisode(PodcastEpisode episode) {
        episode.setEpisodeId(episodeIdCounter++);
        episodes.add(episode);
        return true;
    }

    @Override
    public List<PodcastEpisode> getEpisodesByPodcastId(int podcastId) {
        return episodes.stream()
                .filter(e -> e.getPodcastId() == podcastId)
                .collect(Collectors.toList());
    }

    @Override
    public PodcastEpisode getEpisodeById(int episodeId) {
        return episodes.stream()
                .filter(e -> e.getEpisodeId() == episodeId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean incrementEpisodePlayCount(int episodeId) {
        PodcastEpisode ep = getEpisodeById(episodeId);
        if (ep != null) {
            ep.setPlayCount(ep.getPlayCount() + 1);
            return true;
        }
        return false;
    }
}
