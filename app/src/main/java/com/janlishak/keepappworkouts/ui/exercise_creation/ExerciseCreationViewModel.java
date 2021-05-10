package com.janlishak.keepappworkouts.ui.exercise_creation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExerciseCreationViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExerciseCreationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}