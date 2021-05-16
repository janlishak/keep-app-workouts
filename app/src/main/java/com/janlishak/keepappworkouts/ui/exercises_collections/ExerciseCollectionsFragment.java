package com.janlishak.keepappworkouts.ui.exercises_collections;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.model.ExerciseCollection;

import java.util.List;

public class ExerciseCollectionsFragment extends Fragment {

    private ExerciseCollectionsViewModel viewModel;
    private View root;

    private RecyclerView recyclerView;
    private ExercisesCollectionAdapter exercisesCollectionAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ExerciseCollectionsViewModel.class);
        root = inflater.inflate(R.layout.fragment_exercise_collections, container, false);
        setHasOptionsMenu(true);

        //Binds Toolbar color
        viewModel.getDeleteMode().observe(getViewLifecycleOwner(), deleteMode -> getActivity().findViewById(R.id.toolbar).setBackground(deleteMode? new ColorDrawable(0xFFFF6666):new ColorDrawable(0xFF006666)));

        //RecycleView
        recyclerView = root.findViewById(R.id.rv_exercise_collections);
        exercisesCollectionAdapter = new ExercisesCollectionAdapter();
        exercisesCollectionAdapter.setListener(createRecycleViewOnClickListener());

        viewModel.getExerciseCollections().observe(getViewLifecycleOwner(), new Observer<List<ExerciseCollection>>() {
            @Override
            public void onChanged(List<ExerciseCollection> exerciseCollections) {
                exercisesCollectionAdapter.setData(exerciseCollections);
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(exercisesCollectionAdapter);

        return root;
    }

    private View.OnClickListener createRecycleViewOnClickListener(){
        return view -> {
            ExerciseCollection exerciseCollection = exercisesCollectionAdapter.getExerciseCollection(recyclerView.getChildLayoutPosition(view));
            if (viewModel.getDeleteMode().getValue()) {
                viewModel.remove(exerciseCollection);
            } else {
                Bundle bundle = new Bundle();
                bundle.putSerializable("ec", exerciseCollection);
                Navigation.findNavController(root).navigate(R.id.navigation_exercise_browser, bundle);
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
                Navigation.findNavController(root).navigate(R.id.navigation_exercise_collection_creation);
                return true;
            case R.id.action_delete:
                viewModel.toggleDeleteMode();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}