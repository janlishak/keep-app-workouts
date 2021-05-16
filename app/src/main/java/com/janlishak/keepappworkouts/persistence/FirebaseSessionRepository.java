package com.janlishak.keepappworkouts.persistence;

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
import com.janlishak.keepappworkouts.model.Session;

import java.util.ArrayList;
import java.util.List;


public class FirebaseSessionRepository implements ISessionRepository{
    private static FirebaseSessionRepository INSTANCE;
    private MutableLiveData<List<Session>> sessions;
    private CollectionReference collectionReference;

    public static synchronized ISessionRepository getInstance() {
        if(INSTANCE == null)
            INSTANCE = new FirebaseSessionRepository();
        return INSTANCE;
    }

    private FirebaseSessionRepository() {
        sessions = new MutableLiveData<>();
        sessions.setValue(new ArrayList<>());
    }

    private void refresh(){
        //refresh all collections
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Session> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Session session= document.toObject(Session.class);
                        session.setId(document.getId());
                        list.add(session);
                    }
                    Log.d("db-dbg", list.toString());
                    sessions.setValue(list);
                } else Log.d("db-dbg", "Error getting documents: ", task.getException());
            }
        });
    }

    @Override
    public void setPlan(Plan plan) {
        collectionReference = UserRepository.getInstance().getUserDocument().collection("plan").document(plan.getId()).collection("exercises");
    }

    @Override
    public void addSession(Session Session) {
        System.out.println("adding" + sessions.toString());
        System.out.println("adding" + sessions.toString());
        System.out.println("adding" + sessions.toString());
        System.out.println("adding" + sessions.toString());
        System.out.println("adding" + sessions.toString());
        System.out.println("adding" + sessions.toString());
        System.out.println("adding" + sessions.toString());
        System.out.println("adding" + sessions.toString());

        collectionReference.add(sessions);
        refresh();
    }

    @Override
    public void removeSession(Session session) {
        collectionReference.document(session.getId()).delete();
        refresh();
    }

    @Override
    public LiveData<List<Session>> getSessions() {
        refresh();
        return sessions;
    }
}
