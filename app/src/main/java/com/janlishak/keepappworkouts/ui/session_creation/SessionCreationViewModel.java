package com.janlishak.keepappworkouts.ui.session_creation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.janlishak.keepappworkouts.model.ExerciseCollection;
import com.janlishak.keepappworkouts.model.Session;
import com.janlishak.keepappworkouts.persistence.ISessionRepository;
import com.janlishak.keepappworkouts.persistence.MemoryExerciseCollectionRepository;
import com.janlishak.keepappworkouts.persistence.MemorySessionRepository;

public class SessionCreationViewModel extends ViewModel {

    private MutableLiveData<String> name;
    private ISessionRepository sessionRepository;

    public SessionCreationViewModel() {
        sessionRepository = MemorySessionRepository.getInstance();
        name = new MutableLiveData<>();
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public void createSession() {
        Session session = new Session(name.getValue());
        sessionRepository.addSession(session);
    }
}