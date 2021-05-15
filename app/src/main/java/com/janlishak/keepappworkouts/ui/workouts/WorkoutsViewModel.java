package com.janlishak.keepappworkouts.ui.workouts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janlishak.keepappworkouts.model.Plan;
import com.janlishak.keepappworkouts.model.Session;
import com.janlishak.keepappworkouts.persistence.IPlanRepository;
import com.janlishak.keepappworkouts.persistence.ISessionRepository;
import com.janlishak.keepappworkouts.persistence.MemoryPlanRepository;
import com.janlishak.keepappworkouts.persistence.MemorySessionRepository;

import java.util.List;

public class WorkoutsViewModel extends ViewModel {
    private MutableLiveData<Boolean> deleteMode;
    private ISessionRepository sessionRepository;
    private IPlanRepository planRepository;

    public WorkoutsViewModel() {
        sessionRepository = MemorySessionRepository.getInstance();
        planRepository = MemoryPlanRepository.getInstance();

        deleteMode = new MutableLiveData<>();
        deleteMode.setValue(false);
    }

    public LiveData<Boolean> getDeleteMode() {
        return deleteMode;
    }

    public LiveData<Plan> getActivePlan(){
        return planRepository.getSelectedPlan();
    }

    public LiveData<List<Session>> getSessions() {
        return sessionRepository.getSessions();
    }

    public void remove(Session Session){
        sessionRepository.removeSession(Session);
    }

    public void add(Session Session){
        sessionRepository.removeSession(Session);
    }

    public void toggleDeleteMode() {
        deleteMode.setValue(!deleteMode.getValue());
    }
}