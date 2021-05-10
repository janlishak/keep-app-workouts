package com.janlishak.keepappworkouts.ui.exercise_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExerciseDetailsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExerciseDetailsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}