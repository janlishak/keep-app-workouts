package com.janlishak.keepappworkouts.ui.workouts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janlishak.keepappworkouts.model.Plan;
import com.janlishak.keepappworkouts.model.Workout;
import com.janlishak.keepappworkouts.services.persistance.FirebasePlanRepository;
import com.janlishak.keepappworkouts.services.persistance.IPlanRepository;
import com.janlishak.keepappworkouts.services.persistance.ISessionRepository;
import com.janlishak.keepappworkouts.services.persistance.FirebaseSessionRepository;

import java.util.List;

public class WorkoutsViewModel extends ViewModel {
    private MutableLiveData<Boolean> deleteMode;
    private ISessionRepository sessionRepository;
    private IPlanRepository planRepository;

    public WorkoutsViewModel() {
        sessionRepository = FirebaseSessionRepository.getInstance();
        planRepository = FirebasePlanRepository.getInstance();

        deleteMode = new MutableLiveData<>();
        deleteMode.setValue(false);
    }

    public LiveData<Boolean> getDeleteMode() {
        return deleteMode;
    }

    public LiveData<Plan> getActivePlan(){
        return planRepository.getSelectedPlan();
    }

    public void setPlan(Plan plan){
        sessionRepository.setPlan(plan);
    }

    public LiveData<List<Workout>> getSessions() {
        return sessionRepository.getSessions();
    }

    public void remove(Workout Workout){
        sessionRepository.removeSession(Workout);
    }

    public void add(Workout Workout){
        sessionRepository.removeSession(Workout);
    }

    public void toggleDeleteMode() {
        deleteMode.setValue(!deleteMode.getValue());
    }
}