package com.janlishak.keepappworkouts.ui.exercise_creation;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janlishak.keepappworkouts.model.Exercise;
import com.janlishak.keepappworkouts.services.api.ImgurRepository;
import com.janlishak.keepappworkouts.services.api.Link;
import com.janlishak.keepappworkouts.services.persistance.IExerciseRepository;
import com.janlishak.keepappworkouts.services.persistance.FirebaseExerciseRepository;

public class ExerciseCreationViewModel extends ViewModel {

    private MutableLiveData<String> name;
    private MutableLiveData<String> description;

    private IExerciseRepository exerciseRepository;
    private ImgurRepository repository;
    private LiveData<Link> linkLiveData;

    public ExerciseCreationViewModel() {
        exerciseRepository = FirebaseExerciseRepository.getInstance();
        repository = ImgurRepository.getInstance();

        name = new MutableLiveData<>();
        description = new MutableLiveData<>();
    }

    public LiveData<Link> getImage(){
        return repository.searchForImage("RljrIhF");
    }

    public LiveData<Link> getFile(String  url){
        linkLiveData = repository.uploadImage(url);
        return linkLiveData;
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<String> getDescription() {
        return description;
    }

    public void createExercise() {
        Exercise exerciseToCreate = new Exercise(getName().getValue(), getDescription().getValue());
        Log.i("123", exerciseRepository.getExercises().getValue().toString());
        if(linkLiveData != null && !linkLiveData.getValue().isFailed() && !linkLiveData.getValue().isLoading()){
            Log.i("Retrofit", "ok");
            exerciseToCreate.setImageLink(linkLiveData.getValue().getLink());
            exerciseToCreate.setImageDeleteHash(linkLiveData.getValue().getDeleteHash());
        }
        exerciseRepository.addExercise(exerciseToCreate);
    }
}