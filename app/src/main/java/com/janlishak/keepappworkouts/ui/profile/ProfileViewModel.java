package com.janlishak.keepappworkouts.ui.profile;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.janlishak.keepappworkouts.foreign.Message;
import com.janlishak.keepappworkouts.foreign.MessageRepository;
import com.janlishak.keepappworkouts.foreign.UserRepository;

public class ProfileViewModel extends AndroidViewModel {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public ProfileViewModel(Application app){
        super(app);
        userRepository = UserRepository.getInstance(app);
        messageRepository = MessageRepository.getInstance();
        String userId = userRepository.getCurrentUser().getValue().getUid();
        messageRepository.init(userId);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public void saveMessage(String message) {
        messageRepository.saveMessage(message);
    }

    public LiveData<Message> getMessage() {
        return messageRepository.getMessage();
    }
}