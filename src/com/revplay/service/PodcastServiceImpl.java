package com.revplay.service;

import com.revplay.dao.*;
import com.revplay.model.Podcast;
import java.util.List;

public class PodcastServiceImpl implements IPodcastService {

    private final IPodcastDao dao = new PodcastDaoImpl();

    public void createPodcast(Podcast p) { dao.createPodcast(p); }
    public List<Podcast> getAllPodcasts() { return dao.getAllPodcasts(); }
    public void updatePodcast(Podcast p) { dao.updatePodcast(p); }
    public void deletePodcast(int id) { dao.deletePodcast(id); }
}
