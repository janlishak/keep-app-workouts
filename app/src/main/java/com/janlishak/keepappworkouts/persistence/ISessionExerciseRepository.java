package com.janlishak.keepappworkouts.persistence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.janlishak.keepappworkouts.model.SessionExercise;
import com.janlishak.keepappworkouts.model.Plan;

import java.util.List;

public interface ISessionExerciseRepository {
    void setPlan(Plan plan);
    void addSessionExercise(SessionExercise SessionExercise);
    void removeSessionExercise(SessionExercise SessionExercise);
    MutableLiveData<List<SessionExercise>> getSessionExercises();
}
