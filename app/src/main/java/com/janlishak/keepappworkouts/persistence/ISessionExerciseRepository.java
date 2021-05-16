package com.janlishak.keepappworkouts.persistence;

import androidx.lifecycle.MutableLiveData;

import com.janlishak.keepappworkouts.model.Workout;
import com.janlishak.keepappworkouts.model.SessionExercise;
import com.janlishak.keepappworkouts.model.Plan;

import java.util.List;

public interface ISessionExerciseRepository {
    void setPlan(Plan plan);
    void setSession(Workout workout);
    void addSessionExercise(SessionExercise SessionExercise);
    void removeSessionExercise(SessionExercise SessionExercise);
    MutableLiveData<List<SessionExercise>> getWorkoutExercises();
}
