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
import com.janlishak.keepappworkouts.model.Exercise;
import com.janlishak.keepappworkouts.model.ExerciseCollection;

import java.util.ArrayList;
import java.util.List;

public class FirebaseExerciseRepository implements IExerciseRepository{
    private static FirebaseExerciseRepository INSTANCE;
    private MutableLiveData<List<Exercise>> exercises;
    private CollectionReference collectionReference;

    public static synchronized IExerciseRepository getInstance() {
        if(INSTANCE == null)
            INSTANCE = new FirebaseExerciseRepository();
        return INSTANCE;
    }

    private FirebaseExerciseRepository() {
        exercises = new MutableLiveData<>();
        exercises.setValue(new ArrayList<>());
    }

    private void refresh(){
        exercises.setValue(new ArrayList<>());
        //refresh all collections
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Exercise> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Exercise exercise= document.toObject(Exercise.class);
                        exercise.setId(document.getId());
                        list.add(exercise);
                    }
                    Log.d("db-dbg", list.toString());
                    exercises.setValue(list);
                } else Log.d("db-dbg", "Error getting documents: ", task.getException());
            }
        });
    }

    @Override
    public void setCollection(ExerciseCollection exerciseCollection) {
        collectionReference = UserRepository.getInstance().getUserDocument().collection("exercise-collection").document(exerciseCollection.getId()).collection("exercises");
    }

    @Override
    public void addExercise(Exercise exercise) {
        collectionReference.add(exercise);
        refresh();
    }

    @Override
    public void removeExercise(Exercise exercise) {
        collectionReference.document(exercise.getId()).delete();
        refresh();
    }

    @Override
    public LiveData<List<Exercise>> getExercises() {
        refresh();
        return  exercises;
    }

}
