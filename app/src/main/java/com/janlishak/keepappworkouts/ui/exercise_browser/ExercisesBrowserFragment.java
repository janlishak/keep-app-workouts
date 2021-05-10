package com.janlishak.keepappworkouts.ui.exercise_browser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExercisesBrowserFragment extends Fragment {
    private ExercisesBrowserViewModel exercisesBrowserViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        exercisesBrowserViewModel = new ViewModelProvider(this).get(ExercisesBrowserViewModel.class);
        View root = inflater.inflate(R.layout.fragment_exercise_browser, container, false);
        setHasOptionsMenu(true);

        //get views
        RecyclerView exerciseRecycleView = root.findViewById(R.id.exercise_browser_rw);

        //create sample data
        MutableLiveData<List<Exercise>> exercises = new MutableLiveData<>();
        List<Exercise> sampleData = new ArrayList<>();
        sampleData.add(new Exercise("Push up"));
        sampleData.add(new Exercise("Push up"));
        sampleData.add(new Exercise("Push up"));
        sampleData.add(new Exercise("Push up"));
        sampleData.add(new Exercise("Push up"));
        sampleData.add(new Exercise("Push up"));
        sampleData.add(new Exercise("Push up"));
        sampleData.add(new Exercise("Push up"));
        sampleData.add(new Exercise("Push up"));
        sampleData.add(new Exercise("Push up"));
        sampleData.add(new Exercise("Push up"));
        sampleData.add(new Exercise("Push up"));
        sampleData.add(new Exercise("Push up"));
        sampleData.add(new Exercise("Push up"));
        sampleData.add(new Exercise("Push up"));
        sampleData.add(new Exercise("Push up"));

        exercises.setValue(sampleData);


        ExercisesAdapter exercisesAdapter = new ExercisesAdapter(exercises);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        exerciseRecycleView.setLayoutManager(linearLayoutManager);
        exerciseRecycleView.setAdapter(exercisesAdapter);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.exercises_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}