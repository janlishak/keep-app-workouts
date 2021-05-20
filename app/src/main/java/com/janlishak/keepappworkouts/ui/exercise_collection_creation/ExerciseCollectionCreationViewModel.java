package com.janlishak.keepappworkouts.ui.exercise_collection_creation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janlishak.keepappworkouts.model.ExerciseCollection;
import com.janlishak.keepappworkouts.services.persistance.FirebaseExerciseCollectionRepository;

public class ExerciseCollectionCreationViewModel extends ViewModel {

    private MutableLiveData<String> name;
    private FirebaseExerciseCollectionRepository exerciseCollectionRepository;

    public ExerciseCollectionCreationViewModel() {
        exerciseCollectionRepository = FirebaseExerciseCollectionRepository.getInstance();

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