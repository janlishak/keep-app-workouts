package com.janlishak.keepappworkouts.persistence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.janlishak.keepappworkouts.Exceptions.NotImplementedException;
import com.janlishak.keepappworkouts.foreign.MessageRepository;
import com.janlishak.keepappworkouts.model.Exercise;
import com.janlishak.keepappworkouts.model.ExerciseCollection;

import java.util.ArrayList;
import java.util.List;

public class MemoryExerciseRepository implements IExerciseRepository{
    private static  MemoryExerciseRepository INSTANCE;
    private MutableLiveData<List<Exercise>> exercises;
    private List<Exercise> exercisesList;

    private MemoryExerciseRepository() {
        exercises = new MutableLiveData<>();
        exercisesList = new ArrayList<>();

        loadSampleData();
        }


    public static synchronized IExerciseRepository getInstance() {
        if(INSTANCE == null)
            INSTANCE = new MemoryExerciseRepository();
        return INSTANCE;
    }

    private void loadSampleData(){
        Exercise exercise1 = new Exercise("push-up", "just do a push up");
        Exercise exercise2 = new Exercise("diamond push-up", "just do a push up");
        Exercise exercise3 = new Exercise("easy push-up", "just do a push up");

        exercisesList.add(exercise1);
        exercisesList.add(exercise2);
        exercisesList.add(exercise3);

        exercises.setValue(exercisesList);
    }

    @Override
    public void setCollection(ExerciseCollection exerciseCollection) {
        throw new NotImplementedException();
    }

    @Override
    public void addExercise(Exercise exercise) {
        exercisesList.add(exercise);
        exercises.setValue(exercisesList);
    }

    @Override
    public void removeExercise(Exercise exercise) {
        exercisesList.remove(exercise);
        exercises.setValue(exercisesList);
    }

    @Override
    public LiveData<List<Exercise>> getExercises() {
        return  exercises;
    }

}
