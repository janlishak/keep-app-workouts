package com.janlishak.keepappworkouts.ui.session_exercise_browser;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.janlishak.keepappworkouts.Exceptions.NotImplementedException;
import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.model.Plan;
import com.janlishak.keepappworkouts.model.Workout;
import com.janlishak.keepappworkouts.model.SessionExercise;

public class SessionExercisesBrowserFragment extends Fragment {
    private View root;
    private SessionExercisesBrowserViewModel viewModel;
    private RecyclerView exerciseRecycleView;
    private SessionExercisesAdapter sessionExercisesAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Workout workout = (Workout)getArguments().getSerializable("workout");
        Plan plan = (Plan)getArguments().getSerializable("plan");
        viewModel = new ViewModelProvider(getActivity()).get(SessionExercisesBrowserViewModel.class);
        viewModel.setPlan(plan);
        viewModel.setSession(workout);
        root = inflater.inflate(R.layout.fragment_session_exercise_browser, container, false);
        setHasOptionsMenu(true);

        //Binds Toolbar color
        viewModel.getDeleteMode().observe(getViewLifecycleOwner(), deleteMode -> getActivity().findViewById(R.id.toolbar).setBackground(deleteMode? new ColorDrawable(0xFFFF6666):new ColorDrawable(0xFF006666)));


        //RecycleView
        exerciseRecycleView = root.findViewById(R.id.session_exercise_browser_rw);
        sessionExercisesAdapter = new SessionExercisesAdapter();
        sessionExercisesAdapter.setListener(createRecycleViewOnClickListener());
        sessionExercisesAdapter.setDoneListener(createDoneListener());
        viewModel.getSessionExercises().observe(getViewLifecycleOwner(), exercises -> sessionExercisesAdapter.setData(exercises));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        exerciseRecycleView.setLayoutManager(linearLayoutManager);
        exerciseRecycleView.setAdapter(sessionExercisesAdapter);

        return root;
    }

    private View.OnClickListener createDoneListener(){
        return view ->
        {
            throw new NotImplementedException();
//            SessionExercise exercise = sessionExercisesAdapter.getSessionExercise(exerciseRecycleView.getChildLayoutPosition(view));
//            exercise.setCompleted(!exercise.isCompleted());
//            sessionExercisesAdapter.notifyDataSetChanged();
        };
    }

    private View.OnClickListener createRecycleViewOnClickListener(){
        return view ->
        {
            SessionExercise exercise = sessionExercisesAdapter.getSessionExercise(exerciseRecycleView.getChildLayoutPosition(view));
            if (viewModel.getDeleteMode().getValue()) viewModel.deleteSessionExercise(exercise);
            else {
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
                viewModel.setIsLooking(true);
                Navigation.findNavController(root).navigate(R.id.navigation_exercise_library);
                return true;
            case R.id.action_delete:
                viewModel.toggleDeleteMode();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}