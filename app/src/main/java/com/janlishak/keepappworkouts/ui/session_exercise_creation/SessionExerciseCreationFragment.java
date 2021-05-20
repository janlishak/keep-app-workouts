package com.janlishak.keepappworkouts.ui.session_exercise_creation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.model.Exercise;
import com.janlishak.keepappworkouts.model.SessionExercise;
import com.janlishak.keepappworkouts.ui.session_exercise_browser.SessionExercisesBrowserViewModel;

public class SessionExerciseCreationFragment extends Fragment {
    private View root;
    private SessionExerciseCreationViewModel sessionExerciseCreationViewModel;
    private SessionExercisesBrowserViewModel sharedViewModel;

    private EditText setsTW;
    private EditText restTW;
    private EditText repsTW;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sessionExerciseCreationViewModel = new ViewModelProvider(getActivity()).get(SessionExerciseCreationViewModel.class);
        sharedViewModel = new ViewModelProvider(getActivity()).get(SessionExercisesBrowserViewModel.class);

        root = inflater.inflate(R.layout.fragment_session_exercise_creation, container, false);
        setHasOptionsMenu(true);

        setsTW = root.findViewById(R.id.sets);
        restTW = root.findViewById(R.id.rest);
        repsTW = root.findViewById(R.id.reps);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.creation_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_create:
                Exercise exercise = (Exercise) getArguments().getSerializable("exercise");

                String name = exercise.getName();
                String description = exercise.getDescription();
                Integer sets = Integer.parseInt(setsTW.getText().toString());
                Integer reps = Integer.parseInt(repsTW.getText().toString());
                String rest = restTW.getText().toString();

                SessionExercise sessionExercise = new SessionExercise(name, description, sets, reps, rest);
                sessionExercise.setImageLink(exercise.getImageLink());
                sessionExercise.setImageDeleteHash(exercise.getImageDeleteHash());
                sharedViewModel.setIsLooking(false);
                sharedViewModel.addSessionExercise(sessionExercise);


                ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(root.getWindowToken(), 0);
                Navigation.findNavController(root).popBackStack();
                Navigation.findNavController(root).popBackStack();
                Navigation.findNavController(root).popBackStack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
