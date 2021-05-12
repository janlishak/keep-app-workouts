package com.janlishak.keepappworkouts.authentication_activity;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.janlishak.keepappworkouts.foreign.UserRepository;

public class AuthenticationViewModel extends AndroidViewModel {
    private final UserRepository userRepository;

    public AuthenticationViewModel(Application app){
        super(app);
        userRepository = UserRepository.getInstance(app);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

}
