package com.janlishak.keepappworkouts.ui.session_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SessionDetailsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SessionDetailsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}