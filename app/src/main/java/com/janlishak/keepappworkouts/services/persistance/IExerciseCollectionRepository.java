package com.janlishak.keepappworkouts.services.persistance;

import androidx.lifecycle.LiveData;

import com.janlishak.keepappworkouts.model.ExerciseCollection;

import java.util.List;

public interface IExerciseCollectionRepository {
    LiveData<List<ExerciseCollection>>ExerciseCollection();
    void removeExerciseCollection(ExerciseCollection exerciseCollection);
    void createExerciseCollection(ExerciseCollection exerciseCollection);

}
