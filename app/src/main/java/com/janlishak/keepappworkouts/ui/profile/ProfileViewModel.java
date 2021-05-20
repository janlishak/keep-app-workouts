package com.janlishak.keepappworkouts.ui.profile;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.google.firebase.auth.FirebaseUser;
import com.janlishak.keepappworkouts.services.login.UserRepository;

public class ProfileViewModel extends AndroidViewModel {

    private final UserRepository userRepository;

    public ProfileViewModel(Application app){
        super(app);
        userRepository = UserRepository.getInstance();
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }
}