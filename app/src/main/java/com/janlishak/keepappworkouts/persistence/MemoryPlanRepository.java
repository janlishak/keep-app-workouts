package com.janlishak.keepappworkouts.persistence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.janlishak.keepappworkouts.model.Plan;

import java.util.ArrayList;
import java.util.List;

public class MemoryPlanRepository implements IPlanRepository{
    private List<Plan> list;
    private MutableLiveData<List<Plan>> liveDataList;
    private static MemoryPlanRepository INSTANCE;
    private MutableLiveData<Plan> currentPlan;

    private MemoryPlanRepository(){
        list = new ArrayList<>();
        liveDataList = new MutableLiveData<>();
        loadSampleData();
        currentPlan = new MutableLiveData<>();
        currentPlan.setValue(list.get(0));
    }

    public static synchronized MemoryPlanRepository getInstance() {
        if(INSTANCE == null)
            INSTANCE = new MemoryPlanRepository();
        return INSTANCE;
    }

    public void loadSampleData(){
        list.add(new Plan("calisthenics ", "begginer calisthetics program", "this program server as an introduction to clisthetics"));
        list.add(new Plan("yoga,", "advanced yoga program", "In this short 7 week plan, you'd be thought the more advanced yoga exercises."));
        liveDataList.setValue(list);
    }

    @Override
    public LiveData<List<Plan>> getPlans() {
        return liveDataList;
    }

    @Override
    public void setCurrentPlan(Plan selectedPlan) {
        currentPlan.setValue(selectedPlan);
    }

    @Override
    public LiveData<Plan> getSelectedPlan() {
        return currentPlan;
    }

    @Override
    public void removePlan(Plan plan) {
        list.remove(plan);
        liveDataList.setValue(list);
    }

    @Override
    public void createPlan(Plan plan) {
        list.add(plan);
        liveDataList.setValue(list);
    }


}
