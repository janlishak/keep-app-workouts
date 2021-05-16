package com.janlishak.keepappworkouts.persistence;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.janlishak.keepappworkouts.model.Plan;

import java.util.ArrayList;
import java.util.List;

public class FireStorePlanRepository implements IPlanRepository{

    private static FireStorePlanRepository INSTANCE;

    private MutableLiveData<List<Plan>> liveDataList;
    private MutableLiveData<Plan> currentPlan;

    private CollectionReference collectionReference;
    private CollectionReference currentPlanCollectionReference;

    public static synchronized FireStorePlanRepository getInstance() {
        if(INSTANCE == null)
            INSTANCE = new FireStorePlanRepository();
        return INSTANCE;
    }

    private FireStorePlanRepository(){
        liveDataList = new MutableLiveData<>();
        currentPlan = new MutableLiveData<>();

        collectionReference = FirebaseFirestore.getInstance().collection("plans");
        currentPlanCollectionReference = FirebaseFirestore.getInstance().collection("current-plan");

    }

    private void refresh(){
        //refresh all plans
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Plan> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Plan plan = document.toObject(Plan.class);
                        plan.setId(document.getId());
                        list.add(plan);
                    }
                    Log.d("db-dbg", list.toString());
                    liveDataList.setValue(list);
                } else Log.d("db-dbg", "Error getting documents: ", task.getException());
            }
        });

        //refresh current plan
        Task<DocumentSnapshot> snapshotTask = currentPlanCollectionReference.document("active").get().addOnCompleteListener(task -> {
            if(task.isComplete()){
                Plan plan = task.getResult().toObject(Plan.class);
                currentPlan.setValue(plan);
            }
        });
    }


    @Override
    public LiveData<List<Plan>> getPlans() {
        refresh();
        return liveDataList;
    }

    @Override
    public void setCurrentPlan(Plan selectedPlan) {
        currentPlanCollectionReference.document("active").set(selectedPlan);
        currentPlan.setValue(selectedPlan);
    }

    @Override
    public LiveData<Plan> getSelectedPlan() {
        refresh();
        return currentPlan;
    }

    @Override
    public void removePlan(Plan plan) {
        collectionReference.document(plan.getId()).delete();
        refresh();
    }

    @Override
    public void createPlan(Plan plan) {
        collectionReference.add(plan);
        refresh();
    }
}
