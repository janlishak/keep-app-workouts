package com.janlishak.keepappworkouts.foreign;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

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
}