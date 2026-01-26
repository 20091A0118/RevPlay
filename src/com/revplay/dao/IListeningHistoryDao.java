package com.revplay.dao;

import com.revplay.model.ListeningHistory;
import java.util.List;

public interface IListeningHistoryDao {
    boolean addHistory(ListeningHistory h);
    List<ListeningHistory> getRecent(int userId);
}
