package com.janlishak.keepappworkouts.ui.exercise_browser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExercisesBrowserViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExercisesBrowserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}