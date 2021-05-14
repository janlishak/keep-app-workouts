package com.janlishak.keepappworkouts.ui.exercises_collections;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janlishak.keepappworkouts.model.ExerciseCollection;
import com.janlishak.keepappworkouts.persistence.IExerciseCollectionRepository;
import com.janlishak.keepappworkouts.persistence.MemoryExerciseCollectionRepository;

import java.util.List;

public class ExerciseCollectionsViewModel extends ViewModel {
    private MutableLiveData<Boolean> deleteMode;
    private IExerciseCollectionRepository exerciseCollectionRepository;

    public ExerciseCollectionsViewModel() {
        exerciseCollectionRepository = MemoryExerciseCollectionRepository.getInstance();
        deleteMode = new MutableLiveData<>();
        deleteMode.setValue(false);
    }

    public LiveData<Boolean> getDeleteMode() {
        return deleteMode;
    }

    public LiveData<List<ExerciseCollection>> getExerciseCollections() {
        return exerciseCollectionRepository.ExerciseCollection();
    }

    public void remove(ExerciseCollection exerciseCollection){
        exerciseCollectionRepository.removeExerciseCollection(exerciseCollection);
    }

    public void add(ExerciseCollection exerciseCollection){
        exerciseCollectionRepository.createExerciseCollection(exerciseCollection);
    }

    public void toggleDeleteMode() {
        deleteMode.setValue(!deleteMode.getValue());
    }
}