package com.janlishak.keepappworkouts.ui.session_exercise_browser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janlishak.keepappworkouts.model.Exercise;
import com.janlishak.keepappworkouts.model.Plan;
import com.janlishak.keepappworkouts.model.SessionExercise;
import com.janlishak.keepappworkouts.persistence.ISessionExerciseRepository;
import com.janlishak.keepappworkouts.persistence.MemorySessionExerciseRepository;

import java.util.List;

public class SessionExercisesBrowserViewModel extends ViewModel {
    private MutableLiveData<Exercise> selectedExercise;
    private MutableLiveData<Boolean> isLooking;
    private ISessionExerciseRepository sessionExerciseRepository;
    private MutableLiveData<Boolean> deleteMode;

    public SessionExercisesBrowserViewModel() {
        sessionExerciseRepository = MemorySessionExerciseRepository.getInstance();

        selectedExercise = new MutableLiveData<>();
        isLooking = new MutableLiveData<>();
        deleteMode = new MutableLiveData<Boolean>();

        selectedExercise.setValue(null);
        isLooking.setValue(false);
        deleteMode.setValue(false);
    }

    public MutableLiveData<List<SessionExercise>> getSessionExercises() {
        return sessionExerciseRepository.getSessionExercises();
    }

    public void setPlan(Plan plan){
        sessionExerciseRepository.setPlan(plan);
    }


    public void deleteSessionExercise(SessionExercise SessionExercise){
        sessionExerciseRepository.removeSessionExercise(SessionExercise);
    }

    public void addSessionExercise(SessionExercise sessionExercise){
        sessionExerciseRepository.addSessionExercise(sessionExercise);
    }


    public void toggleDeleteMode() {
        this.deleteMode.setValue(!deleteMode.getValue());
    }

    public LiveData<Boolean> getDeleteMode() {
        return deleteMode;
    }


    public MutableLiveData<Boolean> getIsLooking() {
        return isLooking;
    }

    public void setIsLooking(boolean value){
        this.isLooking.setValue(value);
    }

    public void selectExercise(Exercise exercise) {
        selectedExercise.setValue(exercise);
    }

    public LiveData<Exercise> getSelected() {
        return selectedExercise;
    }
}