package com.janlishak.keepappworkouts.ui.exercise_collection_creation;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janlishak.keepappworkouts.model.Exercise;
import com.janlishak.keepappworkouts.model.ExerciseCollection;
import com.janlishak.keepappworkouts.persistence.IExerciseRepository;
import com.janlishak.keepappworkouts.persistence.MemoryExerciseCollectionRepository;
import com.janlishak.keepappworkouts.persistence.MemoryExerciseRepository;

public class ExerciseCollectionCreationViewModel extends ViewModel {

    private MutableLiveData<String> name;
    private MemoryExerciseCollectionRepository exerciseCollectionRepository;

    public ExerciseCollectionCreationViewModel() {
        exerciseCollectionRepository = MemoryExerciseCollectionRepository.getInstance();

        name = new MutableLiveData<>();
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public void createExerciseCollection() {
        ExerciseCollection exerciseCollection = new ExerciseCollection(getName().getValue());
        exerciseCollectionRepository.createExerciseCollection(exerciseCollection);
    }
}