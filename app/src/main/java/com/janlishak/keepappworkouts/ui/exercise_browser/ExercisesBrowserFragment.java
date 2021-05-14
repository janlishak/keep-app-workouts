package com.janlishak.keepappworkouts.ui.exercise_browser;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.model.Exercise;

import java.util.List;

public class ExercisesBrowserFragment extends Fragment {
    private View root;
    private ExercisesBrowserViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ExercisesBrowserViewModel.class);
        root = inflater.inflate(R.layout.fragment_exercise_browser, container, false);
        setHasOptionsMenu(true);

        //Binds Toolbar color
        viewModel.getDeleteMode().observe(getViewLifecycleOwner(), deleteMode -> getActivity().findViewById(R.id.toolbar).setBackground(deleteMode? new ColorDrawable(0xFFFF6666):new ColorDrawable(0xFF006666)));

        //RecycleView
        RecyclerView exerciseRecycleView = root.findViewById(R.id.exercise_browser_rw);
        ExercisesAdapter exercisesAdapter = new ExercisesAdapter();
        exercisesAdapter.setListener(createRecycleViewOnClickListener(exerciseRecycleView, exercisesAdapter));
        viewModel.getExercises().observe(getViewLifecycleOwner(), new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                exercisesAdapter.setData(exercises);
                Log.i("TAG", "onChanged: datachangedL " + exercises);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        exerciseRecycleView.setLayoutManager(linearLayoutManager);
        exerciseRecycleView.setAdapter(exercisesAdapter);

        return root;
    }

    private View.OnClickListener createRecycleViewOnClickListener(RecyclerView exerciseRecycleView, ExercisesAdapter exercisesAdapter){
        return view -> {
            Exercise exercise = exercisesAdapter.getExercise(exerciseRecycleView.getChildLayoutPosition(view));
            if (viewModel.getDeleteMode().getValue()) {
                viewModel.deleteExercise(exercise);
            } else {

                Bundle bundle = new Bundle();
                bundle.putSerializable("exercise", exercise);
                Navigation.findNavController(root).navigate(R.id.navigation_exercise_details, bundle);
            }
        };
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.delete_add_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Navigation.findNavController(root).navigate(R.id.navigation_exercise_creation);
                return true;
            case R.id.action_delete:
                viewModel.toggleDeleteMode();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}