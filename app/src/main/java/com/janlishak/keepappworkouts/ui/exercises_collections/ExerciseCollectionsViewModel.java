package com.janlishak.keepappworkouts.ui.exercises_collections;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExerciseCollectionsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExerciseCollectionsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}