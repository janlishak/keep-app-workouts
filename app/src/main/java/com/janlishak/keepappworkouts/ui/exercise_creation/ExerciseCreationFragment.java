package com.janlishak.keepappworkouts.ui.exercise_creation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.janlishak.keepappworkouts.R;

public class ExerciseCreationFragment extends Fragment {

    private ExerciseCreationViewModel exerciseCreationViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        exerciseCreationViewModel = new ViewModelProvider(this).get(ExerciseCreationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_exercise_details, container, false);

        setHasOptionsMenu(true);

        return root;
    }
}