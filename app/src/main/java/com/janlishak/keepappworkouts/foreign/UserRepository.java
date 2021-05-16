package com.janlishak.keepappworkouts.foreign;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserRepository {
    private final UserLiveData currentUser;
    private static UserRepository instance;

    private UserRepository() {
        currentUser = new UserLiveData();
    }

    public static synchronized UserRepository getInstance() {
        if(instance == null)
            instance = new UserRepository();
        return instance;
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }

    public DocumentReference getUserDocument(){
        String userId = UserRepository.getInstance().getCurrentUser().getValue().getUid();
        return FirebaseFirestore.getInstance().collection("user").document(userId);
    }
}