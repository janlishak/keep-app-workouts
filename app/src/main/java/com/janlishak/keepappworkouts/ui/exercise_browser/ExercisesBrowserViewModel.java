package com.janlishak.keepappworkouts.ui.exercise_browser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janlishak.keepappworkouts.model.Exercise;
import com.janlishak.keepappworkouts.model.ExerciseCollection;
import com.janlishak.keepappworkouts.persistence.IExerciseRepository;
import com.janlishak.keepappworkouts.persistence.MemoryExerciseRepository;

import java.util.List;

public class ExercisesBrowserViewModel extends ViewModel {
    private IExerciseRepository exerciseRepository;
    private MutableLiveData<Boolean> deleteMode;

    public ExercisesBrowserViewModel() {
        exerciseRepository = MemoryExerciseRepository.getInstance();

        deleteMode = new MutableLiveData<Boolean>();
        deleteMode.setValue(false);
    }
    public LiveData<List<Exercise>> getExercises(){
        return exerciseRepository.getExercises();
    }

    public void setExerciseCollection(ExerciseCollection exerciseCollection){
        exerciseRepository.setCollection(exerciseCollection);
    }


    public void deleteExercise(Exercise exercise){
        exerciseRepository.removeExercise(exercise);
    }


    public void toggleDeleteMode() {
        this.deleteMode.setValue(!deleteMode.getValue());
    }

    public LiveData<Boolean> getDeleteMode() {
        return deleteMode;
    }
}