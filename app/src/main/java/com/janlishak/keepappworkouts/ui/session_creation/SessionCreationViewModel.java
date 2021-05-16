package com.janlishak.keepappworkouts.ui.session_creation;

import androidx.lifecycle.ViewModel;

import com.janlishak.keepappworkouts.model.Workout;
import com.janlishak.keepappworkouts.persistence.ISessionRepository;
import com.janlishak.keepappworkouts.persistence.FirebaseSessionRepository;

public class SessionCreationViewModel extends ViewModel {

    private ISessionRepository sessionRepository;

    public SessionCreationViewModel() {
        sessionRepository = FirebaseSessionRepository.getInstance();
    }

    public void createSession(String name) {
        Workout workout = new Workout(name);
        sessionRepository.addSession(workout);
    }
}