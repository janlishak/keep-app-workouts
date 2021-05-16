package com.janlishak.keepappworkouts.ui.session_exercise_creation;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janlishak.keepappworkouts.model.Exercise;
import com.janlishak.keepappworkouts.persistence.IExerciseRepository;
import com.janlishak.keepappworkouts.persistence.MemoryExerciseRepository;

public class SessionExerciseCreationViewModel extends ViewModel {

    private MutableLiveData<String> name;
    private MutableLiveData<String> description;

    private IExerciseRepository exerciseRepository;

    public SessionExerciseCreationViewModel() {
        exerciseRepository = MemoryExerciseRepository.getInstance();

        name = new MutableLiveData<>();
        description = new MutableLiveData<>();
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<String> getDescription() {
        return description;
    }

    public void createExercise() {
        Exercise exerciseToCreate = new Exercise(getName().getValue(), getDescription().getValue());
        Log.i("123", exerciseRepository.getExercises().getValue().toString());
        exerciseRepository.addExercise(exerciseToCreate);
    }
}