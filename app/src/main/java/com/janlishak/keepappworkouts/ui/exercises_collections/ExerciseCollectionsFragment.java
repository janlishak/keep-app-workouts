package com.janlishak.keepappworkouts.ui.exercises_collections;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.janlishak.keepappworkouts.R;

import java.util.ArrayList;
import java.util.List;

public class ExerciseCollectionsFragment extends Fragment {

    private ExerciseCollectionsViewModel exerciseCollectionsViewModel;

    private RecyclerView dataList;
    List<String> titles = new ArrayList<>();
    List<Integer> images = new ArrayList<>();
    ExercisesCollectionAdapter exercisesCollectionAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        exerciseCollectionsViewModel = new ViewModelProvider(this).get(ExerciseCollectionsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_exercise_collections, container, false);
        setHasOptionsMenu(true);

        dataList = root.findViewById(R.id.dataList);



        titles.add("First Item");
        titles.add("Second Item");
        titles.add("Third Item");
        titles.add("Fourth Item");

        images.add(R.drawable.wk1);
        images.add(R.drawable.wk1);
        images.add(R.drawable.wk1);
        images.add(R.drawable.wk1);

        exercisesCollectionAdapter = new ExercisesCollectionAdapter(getContext(),titles,images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(exercisesCollectionAdapter);//set adapter to recyclerview

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.exercises_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}