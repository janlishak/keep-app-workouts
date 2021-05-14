package com.janlishak.keepappworkouts.persistence;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.janlishak.keepappworkouts.model.ExerciseCollection;

import java.util.ArrayList;
import java.util.List;

public class MemoryExerciseCollectionRepository implements IExerciseCollectionRepository{
    private List<ExerciseCollection> list;
    private MutableLiveData<List<ExerciseCollection>> liveDataList;
    private static MemoryExerciseCollectionRepository INSTANCE;

    private MemoryExerciseCollectionRepository(){
        list = new ArrayList<>();
        liveDataList = new MutableLiveData<>();
        loadSampleData();
    }

    public static synchronized MemoryExerciseCollectionRepository getInstance() {
        if(INSTANCE == null)
            INSTANCE = new MemoryExerciseCollectionRepository();
        return INSTANCE;
    }

    public void loadSampleData(){
        list.add(new ExerciseCollection("ring exercises"));
        list.add(new ExerciseCollection("own weight"));
        liveDataList.setValue(list);
    }

    @Override
    public LiveData<List<ExerciseCollection>> ExerciseCollection() {
        return liveDataList;
    }

    @Override
    public void removeExerciseCollection(ExerciseCollection exerciseCollection) {
        list.remove(exerciseCollection);
        liveDataList.setValue(list);
    }

    @Override
    public void createExerciseCollection(ExerciseCollection exerciseCollection) {
        list.add(exerciseCollection);
        liveDataList.setValue(list);
    }


}
