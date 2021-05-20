package com.janlishak.keepappworkouts.services.persistance;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.janlishak.keepappworkouts.foreign.UserRepository;
import com.janlishak.keepappworkouts.model.Plan;
import com.janlishak.keepappworkouts.model.Workout;

import java.util.ArrayList;
import java.util.List;


public class FirebaseSessionRepository implements ISessionRepository{
    private static FirebaseSessionRepository INSTANCE;
    private MutableLiveData<List<Workout>> workouts;
    private CollectionReference collectionReference;

    public static synchronized ISessionRepository getInstance() {
        if(INSTANCE == null)
            INSTANCE = new FirebaseSessionRepository();
        return INSTANCE;
    }

    private FirebaseSessionRepository() {
        workouts = new MutableLiveData<>();
        workouts.setValue(new ArrayList<>());
    }

    private void refresh(){
        workouts.setValue(new ArrayList<>());
        //refresh all collections
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Workout> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Workout workout = document.toObject(Workout.class);
                        workout.setId(document.getId());
                        list.add(workout);
                    }
                    Log.d("db-dbg", list.toString());
                    workouts.setValue(list);
                } else Log.d("db-dbg", "Error getting documents: ", task.getException());
            }
        });
    }

    @Override
    public void setPlan(Plan plan) {
        collectionReference = UserRepository.getInstance().getUserDocument().collection("plan").document(plan.getId()).collection("session");
    }

    @Override
    public void addSession(Workout workout) {
        collectionReference.add(workout);
        refresh();
    }

    @Override
    public void removeSession(Workout workout) {
        collectionReference.document(workout.getId()).delete();
        refresh();
    }

    @Override
    public LiveData<List<Workout>> getSessions() {
        refresh();
        return workouts;
    }
}
