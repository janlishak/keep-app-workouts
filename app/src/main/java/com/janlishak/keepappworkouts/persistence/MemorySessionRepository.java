package com.janlishak.keepappworkouts.persistence;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.janlishak.keepappworkouts.Exceptions.NotImplementedException;
import com.janlishak.keepappworkouts.model.Session;
import com.janlishak.keepappworkouts.model.Plan;

import java.util.ArrayList;
import java.util.List;

public class MemorySessionRepository implements ISessionRepository{
    private static MemorySessionRepository INSTANCE;
    private MutableLiveData<List<Session>> sessions;
    private List<Session> sessionsList;

    private MemorySessionRepository() {
        sessions = new MutableLiveData<>();
        sessionsList = new ArrayList<>();

        loadSampleData();
        }


    public static synchronized ISessionRepository getInstance() {
        if(INSTANCE == null)
            INSTANCE = new MemorySessionRepository();
        return INSTANCE;
    }

    private void loadSampleData(){
        Session Session1 = new Session("session 1");
        Session Session2 = new Session("session 2");
        Session Session3 = new Session("session 3");

        sessionsList.add(Session1);
        sessionsList.add(Session2);
        sessionsList.add(Session3);

        sessions.setValue(sessionsList);
    }

    @Override
    public void setPlan(Plan plan) {
        Log.i("db-dbg", plan.toString());
    }

    @Override
    public void addSession(Session Session) {
        sessionsList.add(Session);
        sessions.setValue(sessionsList);
    }

    @Override
    public void removeSession(Session Session) {
        sessionsList.remove(Session);
        sessions.setValue(sessionsList);
    }

    @Override
    public LiveData<List<Session>> getSessions() {
        return  sessions;
    }

}
