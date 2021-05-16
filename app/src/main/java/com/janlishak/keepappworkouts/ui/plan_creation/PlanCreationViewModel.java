package com.janlishak.keepappworkouts.ui.plan_creation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janlishak.keepappworkouts.model.ExerciseCollection;
import com.janlishak.keepappworkouts.model.Plan;
import com.janlishak.keepappworkouts.persistence.FireStorePlanRepository;
import com.janlishak.keepappworkouts.persistence.IPlanRepository;
import com.janlishak.keepappworkouts.persistence.MemoryPlanRepository;

public class PlanCreationViewModel extends ViewModel {

    private MutableLiveData<String> name;
    private MutableLiveData<String> description;

    private IPlanRepository planRepository;

    public PlanCreationViewModel() {
        planRepository = FireStorePlanRepository.getInstance();
        name = new MutableLiveData<>();
        description = new MutableLiveData<>();
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<String> getDescription() {
        return description;
    }

    public void createPlan() {
        Plan plan = new Plan(name.getValue(), description.getValue());
        planRepository.createPlan(plan);
    }
}