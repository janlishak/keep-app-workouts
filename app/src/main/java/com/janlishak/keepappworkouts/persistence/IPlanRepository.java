package com.janlishak.keepappworkouts.persistence;

import androidx.lifecycle.LiveData;

import com.janlishak.keepappworkouts.model.Plan;

import java.util.List;

public interface IPlanRepository {
    LiveData<List<Plan>> getPlans();
    void setCurrentPlan(Plan selectedPlan);
    LiveData<Plan> getSelectedPlan();
    void removePlan(Plan Plan);
    void createPlan(Plan Plan);
}
