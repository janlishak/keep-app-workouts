package com.janlishak.keepappworkouts.ui.plans;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janlishak.keepappworkouts.model.Plan;
import com.janlishak.keepappworkouts.persistence.IPlanRepository;
import com.janlishak.keepappworkouts.persistence.MemoryPlanRepository;

import java.util.List;

public class PlansViewModel extends ViewModel {
    private MutableLiveData<Boolean> deleteMode;
    private IPlanRepository planRepository;

    public PlansViewModel() {
        planRepository = MemoryPlanRepository.getInstance();
        deleteMode = new MutableLiveData<>();
        deleteMode.setValue(false);
    }

    public LiveData<Boolean> getDeleteMode() {
        return deleteMode;
    }

    public LiveData<List<Plan>> getPlans() {
        return planRepository.getPlans();
    }

    public void remove(Plan plan){
        planRepository.removePlan(plan);
    }

    public void add(Plan plan){
        planRepository.removePlan(plan);
    }

    public void setActivePlan(Plan plan){
        planRepository.setCurrentPlan(plan);
    }

    public void toggleDeleteMode() {
        deleteMode.setValue(!deleteMode.getValue());
    }
}