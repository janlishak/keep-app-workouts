package com.janlishak.keepappworkouts.ui.session_exercise_browser;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janlishak.keepappworkouts.model.Exercise;
import com.janlishak.keepappworkouts.model.Plan;
import com.janlishak.keepappworkouts.model.Workout;
import com.janlishak.keepappworkouts.model.SessionExercise;
import com.janlishak.keepappworkouts.services.persistance.ISessionExerciseRepository;
import com.janlishak.keepappworkouts.services.persistance.FirebaseSessionExerciseRepository;

import java.util.List;

public class SessionExercisesBrowserViewModel extends ViewModel {
    private MutableLiveData<Exercise> selectedExercise;
    private MutableLiveData<Boolean> isLooking;
    private ISessionExerciseRepository sessionExerciseRepository;
    private MutableLiveData<Boolean> deleteMode;

    public SessionExercisesBrowserViewModel() {
        sessionExerciseRepository = FirebaseSessionExerciseRepository.getInstance();

        selectedExercise = new MutableLiveData<>();
        isLooking = new MutableLiveData<>();
        deleteMode = new MutableLiveData<Boolean>();

        selectedExercise.setValue(null);
        isLooking.setValue(false);
        deleteMode.setValue(false);
    }

    public MutableLiveData<List<SessionExercise>> getSessionExercises() {
        return sessionExerciseRepository.getWorkoutExercises();
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

    public void setSession(Workout workout) {
        sessionExerciseRepository.setSession(workout);
    }

    public void completeExercise(int childLayoutPosition) {
//        This method is not fully functional yey
//        It has been disabled because it causes crashes sometimes
//
//        List<SessionExercise> exercises = getSessionExercises().getValue();
//        SessionExercise completedExercise = exercises.get(childLayoutPosition);
//        sessionExerciseRepository.removeSessionExercise(completedExercise);
//        completedExercise.setCompleted(true);
//        sessionExerciseRepository.addSessionExercise(completedExercise);

    }
}