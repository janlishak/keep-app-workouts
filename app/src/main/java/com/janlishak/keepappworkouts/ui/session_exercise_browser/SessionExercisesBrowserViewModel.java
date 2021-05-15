package com.janlishak.keepappworkouts.ui.session_exercise_browser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janlishak.keepappworkouts.model.Plan;
import com.janlishak.keepappworkouts.model.SessionExercise;
import com.janlishak.keepappworkouts.persistence.ISessionExerciseRepository;
import com.janlishak.keepappworkouts.persistence.MemorySessionExerciseRepository;

import java.util.List;

public class SessionExercisesBrowserViewModel extends ViewModel {
    private ISessionExerciseRepository sessionExerciseRepository;
    private MutableLiveData<Boolean> deleteMode;

    public SessionExercisesBrowserViewModel() {
        sessionExerciseRepository = MemorySessionExerciseRepository.getInstance();

        deleteMode = new MutableLiveData<Boolean>();
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


    public void toggleDeleteMode() {
        this.deleteMode.setValue(!deleteMode.getValue());
    }

    public LiveData<Boolean> getDeleteMode() {
        return deleteMode;
    }
}