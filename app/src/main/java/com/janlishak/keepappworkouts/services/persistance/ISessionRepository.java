package com.janlishak.keepappworkouts.services.persistance;

import androidx.lifecycle.LiveData;

import com.janlishak.keepappworkouts.model.Plan;
import com.janlishak.keepappworkouts.model.Workout;

import java.util.List;

public interface ISessionRepository {
    void setPlan(Plan plan);
    void addSession(Workout workout);
    void removeSession(Workout workout);
    LiveData<List<Workout>> getSessions();
}
