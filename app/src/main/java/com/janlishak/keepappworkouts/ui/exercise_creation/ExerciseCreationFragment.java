package com.janlishak.keepappworkouts.ui.exercise_creation;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

public class ExerciseCreationFragment extends Fragment {
    private View root;
    private ExerciseCreationViewModel exerciseCreationViewModel;
    private EditText name;
    private EditText description;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        exerciseCreationViewModel = new ViewModelProvider(this).get(ExerciseCreationViewModel.class);
        root = inflater.inflate(R.layout.fragment_exercise_creation, container, false);
        setHasOptionsMenu(true);

        name = root.findViewById(R.id.exercise_name_edit_text);
        description = root.findViewById(R.id.exercise_description_edit_text);
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
                exerciseCreationViewModel.getName().setValue(name.getText().toString());
                exerciseCreationViewModel.getDescription().setValue(description.getText().toString());
                exerciseCreationViewModel.createExercise();
                ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(root.getWindowToken(), 0);
                Navigation.findNavController(root).popBackStack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
