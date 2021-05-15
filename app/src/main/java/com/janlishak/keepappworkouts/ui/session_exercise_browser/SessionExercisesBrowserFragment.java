package com.janlishak.keepappworkouts.ui.session_exercise_browser;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.model.SessionExercise;

import org.w3c.dom.Text;

import java.util.List;

public class SessionExercisesBrowserFragment extends Fragment {
    private View root;
    private SessionExercisesBrowserViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(SessionExercisesBrowserViewModel.class);
        root = inflater.inflate(R.layout.fragment_session_exercise_browser, container, false);
        setHasOptionsMenu(true);

        //Binds Toolbar color
        viewModel.getDeleteMode().observe(getViewLifecycleOwner(), deleteMode -> getActivity().findViewById(R.id.toolbar).setBackground(deleteMode? new ColorDrawable(0xFFFF6666):new ColorDrawable(0xFF006666)));


        //RecycleView
        RecyclerView exerciseRecycleView = root.findViewById(R.id.session_exercise_browser_rw);
        SessionExercisesAdapter SessionExercisesAdapter = new SessionExercisesAdapter();
        SessionExercisesAdapter.setListener(createRecycleViewOnClickListener(exerciseRecycleView, SessionExercisesAdapter));
        viewModel.getSessionExercises().observe(getViewLifecycleOwner(), exercises -> SessionExercisesAdapter.setData(exercises));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        exerciseRecycleView.setLayoutManager(linearLayoutManager);
        exerciseRecycleView.setAdapter(SessionExercisesAdapter);

        return root;
    }

    private View.OnClickListener createRecycleViewOnClickListener(RecyclerView exerciseRecycleView, SessionExercisesAdapter SessionExercisesAdapter){
        return view ->
        {
                SessionExercise exercise = SessionExercisesAdapter.getSessionExercise(exerciseRecycleView.getChildLayoutPosition(view));
                exercise.setCompleted(!exercise.isCompleted());
                SessionExercisesAdapter.notifyDataSetChanged();
        };
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.delete_add_toolbar, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }

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