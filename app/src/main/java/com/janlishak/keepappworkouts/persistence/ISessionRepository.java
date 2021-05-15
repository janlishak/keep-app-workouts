package com.janlishak.keepappworkouts.persistence;

import androidx.lifecycle.LiveData;

import com.janlishak.keepappworkouts.model.Plan;
import com.janlishak.keepappworkouts.model.Session;

import java.util.List;

public interface ISessionRepository {
    void setPlan(Plan plan);
    void addSession(Session session);
    void removeSession(Session session);
    LiveData<List<Session>> getSessions();
}
