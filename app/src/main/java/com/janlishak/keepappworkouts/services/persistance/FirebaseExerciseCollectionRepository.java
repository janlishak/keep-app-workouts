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
import com.janlishak.keepappworkouts.model.ExerciseCollection;

import java.util.ArrayList;
import java.util.List;

public class FirebaseExerciseCollectionRepository implements IExerciseCollectionRepository{
    private static FirebaseExerciseCollectionRepository INSTANCE;
    private MutableLiveData<List<ExerciseCollection>> liveDataList;
    private CollectionReference collectionReference;

    public static synchronized FirebaseExerciseCollectionRepository getInstance() {
        if(INSTANCE == null)
            INSTANCE = new FirebaseExerciseCollectionRepository();
        return INSTANCE;
    }

    private FirebaseExerciseCollectionRepository(){
        liveDataList = new MutableLiveData<>();
        liveDataList.setValue(new ArrayList<>());
        collectionReference = UserRepository.getInstance().getUserDocument().collection("exercise-collection");
    }

    private void refresh(){
        liveDataList.setValue(new ArrayList<>());
        //refresh all collections
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<ExerciseCollection> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        ExerciseCollection exerciseCollection= document.toObject(ExerciseCollection.class);
                        exerciseCollection.setId(document.getId());
                        list.add(exerciseCollection);
                    }
                    Log.d("db-dbg", list.toString());
                    liveDataList.setValue(list);
                } else Log.d("db-dbg", "Error getting documents: ", task.getException());
            }
        });
    }

    @Override
    public LiveData<List<ExerciseCollection>> ExerciseCollection() {
        refresh();
        return liveDataList;
    }

    @Override
    public void removeExerciseCollection(ExerciseCollection exerciseCollection) {
        collectionReference.document(exerciseCollection.getId()).delete();
        refresh();
    }

    @Override
    public void createExerciseCollection(ExerciseCollection exerciseCollection) {
        collectionReference.add(exerciseCollection);
        refresh();
    }


}
