package com.revplay.service;

import com.revplay.model.ListeningHistory;
import java.util.List;

public interface IListeningHistoryService {
    boolean add(ListeningHistory h);
    List<ListeningHistory> recent(int userId);
}
