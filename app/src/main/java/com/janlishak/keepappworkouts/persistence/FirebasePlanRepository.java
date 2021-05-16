package com.janlishak.keepappworkouts.persistence;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.janlishak.keepappworkouts.foreign.UserRepository;
import com.janlishak.keepappworkouts.model.Plan;

import java.util.ArrayList;
import java.util.List;

public class FirebasePlanRepository implements IPlanRepository{

    private static FirebasePlanRepository INSTANCE;

    private MutableLiveData<List<Plan>> liveDataList;
    private MutableLiveData<Plan> currentPlan;

    private CollectionReference collectionReference;
    private CollectionReference currentPlanCollectionReference;

    public static synchronized FirebasePlanRepository getInstance() {
        if(INSTANCE == null)
            INSTANCE = new FirebasePlanRepository();
        return INSTANCE;
    }

    private FirebasePlanRepository(){
        liveDataList = new MutableLiveData<>();
        liveDataList.setValue(new ArrayList<>());
        currentPlan = new MutableLiveData<>();

        DocumentReference userDocumentReference = UserRepository.getInstance().getUserDocument();
        collectionReference = userDocumentReference.collection("plan");
        currentPlanCollectionReference = userDocumentReference.collection("current");

    }

    private void refresh(){
        liveDataList.setValue(new ArrayList<>());
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
        Task<DocumentSnapshot> snapshotTask = currentPlanCollectionReference.document("plan").get().addOnCompleteListener(task -> {
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
        currentPlanCollectionReference.document("plan").set(selectedPlan);
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
