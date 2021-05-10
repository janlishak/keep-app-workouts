package com.janlishak.keepappworkouts.ui.exercise_browser;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.activities.NotificationsActivity;
import com.janlishak.keepappworkouts.activities.SettingsActivity;
import com.janlishak.keepappworkouts.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExercisesBrowserFragment extends Fragment {
    private View root;
    private ExercisesBrowserViewModel exercisesBrowserViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        exercisesBrowserViewModel = new ViewModelProvider(this).get(ExercisesBrowserViewModel.class);
        root = inflater.inflate(R.layout.fragment_exercise_browser, container, false);
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
        inflater.inflate(R.menu.exercise_browser_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_exercise:
                Navigation.findNavController(root).navigate(R.id.navigation_exercise_creation);
                return true;

            case R.id.action_delete_exercise:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}