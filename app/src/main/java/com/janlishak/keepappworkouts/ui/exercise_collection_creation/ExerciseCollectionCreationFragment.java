package com.janlishak.keepappworkouts.ui.exercise_collection_creation;

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

public class ExerciseCollectionCreationFragment extends Fragment {
    private View root;
    private ExerciseCollectionCreationViewModel exerciseCollectionCreationViewModel;
    private EditText name;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        exerciseCollectionCreationViewModel = new ViewModelProvider(this).get(ExerciseCollectionCreationViewModel.class);
        root = inflater.inflate(R.layout.fragment_exercise_collection_creation, container, false);
        setHasOptionsMenu(true);

        name = root.findViewById(R.id.exercise_collection_name_edit_text);

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
                exerciseCollectionCreationViewModel.getName().setValue(name.getText().toString());
                exerciseCollectionCreationViewModel.createExerciseCollection();
                ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(root.getWindowToken(), 0);
                Navigation.findNavController(root).popBackStack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
