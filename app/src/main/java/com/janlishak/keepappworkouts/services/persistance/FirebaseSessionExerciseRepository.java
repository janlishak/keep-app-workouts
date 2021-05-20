package com.janlishak.keepappworkouts.services.persistance;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.janlishak.keepappworkouts.services.login.UserRepository;
import com.janlishak.keepappworkouts.model.Plan;
import com.janlishak.keepappworkouts.model.SessionExercise;
import com.janlishak.keepappworkouts.model.Workout;

import java.util.ArrayList;
import java.util.List;

public class FirebaseSessionExerciseRepository implements ISessionExerciseRepository {
    private static FirebaseSessionExerciseRepository INSTANCE;
    private MutableLiveData<List<SessionExercise>> workoutExercises;
    private CollectionReference planReference;
    private CollectionReference workoutReference;

    public static synchronized ISessionExerciseRepository getInstance() {
        if(INSTANCE == null)
            INSTANCE = new FirebaseSessionExerciseRepository();
        return INSTANCE;
    }

    private FirebaseSessionExerciseRepository() {
        workoutExercises = new MutableLiveData<>();
        workoutExercises.setValue(new ArrayList<>());
    }

    private void refresh(){
        workoutExercises.setValue(new ArrayList<>());
        //refresh all collections
        workoutReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<SessionExercise> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        SessionExercise exercise= document.toObject(SessionExercise.class);
                        exercise.setId(document.getId());
                        list.add(exercise);
                    }
                    Log.d("db-dbg", list.toString());
                    workoutExercises.setValue(list);
                } else Log.d("db-dbg", "Error getting documents: ", task.getException());
            }
        });
    }

    @Override
    public void setPlan(Plan plan) {
        planReference = UserRepository.getInstance().getUserDocument().collection("plan").document(plan.getId()).collection("session");
    }

    @Override
    public void setSession(Workout workout) {
        workoutReference = planReference.document(workout.getId()).collection("exercises");
    }

    @Override
    public void addSessionExercise(SessionExercise sessionExercise) {
        workoutReference.add(sessionExercise);
        refresh();
    }

    @Override
    public void removeSessionExercise(SessionExercise sessionExercise) {
        workoutReference.add(sessionExercise);
        refresh();
    }

    @Override
    public MutableLiveData<List<SessionExercise>> getWorkoutExercises() {
        refresh();
        return workoutExercises;
    }
}
