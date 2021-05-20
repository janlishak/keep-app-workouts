package com.janlishak.keepappworkouts.ui.exercise_details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.model.Exercise;

public class ExerciseDetailsFragment extends Fragment {

    private ExerciseDetailsViewModel exerciseDetailsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        exerciseDetailsViewModel = new ViewModelProvider(this).get(ExerciseDetailsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_exercise_details, container, false);
        setHasOptionsMenu(true);

        TextView name = root.findViewById(R.id.detail_exercise_name);
        TextView description = root.findViewById(R.id.detail_exercise_description);
        ImageView image = root.findViewById(R.id.detail_exercise_image);


        Exercise exercise = (Exercise) getArguments().getSerializable("exercise");
        name.setText(exercise.getName());
        description.setText(exercise.getDescription());

        Glide.with(getContext()).load(exercise.getImageLink()).into(image);
        return root;
    }
}