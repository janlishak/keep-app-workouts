package com.janlishak.keepappworkouts.foreign;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MessageRepository {
    private static MessageRepository instance;
    private DatabaseReference myRef;
    private MessageLiveData message;

    public static synchronized MessageRepository getInstance() {
        if(instance == null)
            instance = new MessageRepository();
        return instance;
    }

    private MessageRepository(){
        String userId = UserRepository.getInstance().getCurrentUser().getValue().getUid();
        myRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
        message = new MessageLiveData(myRef);
    }


    public void saveMessage(String message) {
        myRef.setValue(new Message(message));
    }

    public MessageLiveData getMessage() {
        return message;
    }
}