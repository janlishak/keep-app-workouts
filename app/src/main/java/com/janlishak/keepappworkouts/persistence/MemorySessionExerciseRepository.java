package com.janlishak.keepappworkouts.persistence;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.janlishak.keepappworkouts.Exceptions.NotImplementedException;
import com.janlishak.keepappworkouts.model.Plan;
import com.janlishak.keepappworkouts.model.Session;
import com.janlishak.keepappworkouts.model.SessionExercise;

import java.util.ArrayList;
import java.util.List;

public class MemorySessionExerciseRepository implements ISessionExerciseRepository{
    private static MemorySessionExerciseRepository INSTANCE;
    private MutableLiveData<List<SessionExercise>> SessionExercises;
    private List<SessionExercise> SessionExercisesList;

    private MemorySessionExerciseRepository() {
        SessionExercises = new MutableLiveData<>();
        SessionExercisesList = new ArrayList<>();

        loadSampleData();
        }


    public static synchronized ISessionExerciseRepository getInstance() {
        if(INSTANCE == null)
            INSTANCE = new MemorySessionExerciseRepository();
        return INSTANCE;
    }

    private void loadSampleData(){
        SessionExercise SessionExercise1 = new SessionExercise("push-up", "just do a push up", 4, 20, "1min", true);
        SessionExercise SessionExercise2 = new SessionExercise("diamond push-up", "just do a push up", 3, 10, "30sec");
        SessionExercise SessionExercise3 = new SessionExercise("easy push-up", "just do a push up", 2, 12, "1min");

        SessionExercisesList.add(SessionExercise1);
        SessionExercisesList.add(SessionExercise2);
        SessionExercisesList.add(SessionExercise3);

        SessionExercises.setValue(SessionExercisesList);
    }

    @Override
    public void setPlan(Plan plan) {
        Log.i("db-dbg", plan.toString());
    }

    @Override
    public void setSession(Session session) {
        Log.i("db-dbg", session.toString());
    }

    @Override
    public void addSessionExercise(SessionExercise SessionExercise) {
        SessionExercisesList.add(SessionExercise);
        SessionExercises.setValue(SessionExercisesList);
    }

    @Override
    public void removeSessionExercise(SessionExercise SessionExercise) {
        SessionExercisesList.remove(SessionExercise);
        SessionExercises.setValue(SessionExercisesList);
    }

    @Override
    public MutableLiveData<List<SessionExercise>> getSessionExercises() {
        return  SessionExercises;
    }

}
