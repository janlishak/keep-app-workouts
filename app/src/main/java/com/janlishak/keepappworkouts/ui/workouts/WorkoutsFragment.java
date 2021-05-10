package com.janlishak.keepappworkouts.ui.workouts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.model.Session;

import java.util.ArrayList;
import java.util.List;

public class WorkoutsFragment extends Fragment {

    private WorkoutsViewModel workoutsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        workoutsViewModel = new ViewModelProvider(this).get(WorkoutsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_workouts, container, false);

        //get views
        RecyclerView recyclerView = root.findViewById(R.id.SessionsRecyclerView);

        //create sample data
        MutableLiveData<List<Session>> sessions = new MutableLiveData<>();
        List<Session> sampleData = new ArrayList<>();
        sampleData.add(new Session("Abs with weights"));
        sampleData.add(new Session("Lower body"));
        sampleData.add(new Session("Core with weights"));
        sampleData.add(new Session("Chest"));
        sampleData.add(new Session("Upper body"));
        sessions.setValue(sampleData);


        SessionAdapter sessionAdapter = new SessionAdapter(sessions);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(sessionAdapter);

        return root;
    }
}