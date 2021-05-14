package com.janlishak.keepappworkouts.persistence;

import androidx.lifecycle.LiveData;

import com.janlishak.keepappworkouts.model.Exercise;
import com.janlishak.keepappworkouts.model.ExerciseCollection;

import java.util.List;

public interface IExerciseRepository {
    void setCollection(ExerciseCollection exerciseCollection);
    void addExercise(Exercise exercise);
    void removeExercise(Exercise exercise);
    LiveData<List<Exercise>> getExercises();
}
