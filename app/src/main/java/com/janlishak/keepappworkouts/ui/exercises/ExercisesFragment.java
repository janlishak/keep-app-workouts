package com.janlishak.keepappworkouts.ui.exercises;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.janlishak.keepappworkouts.ExercisesAdapter;
import com.janlishak.keepappworkouts.R;

import java.util.ArrayList;
import java.util.List;

public class ExercisesFragment extends Fragment {

    private ExercisesViewModel exercisesViewModel;

    private RecyclerView dataList;
    List<String> titles = new ArrayList<>();
    List<Integer> images = new ArrayList<>();
    ExercisesAdapter  exercisesAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        exercisesViewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_exercises, container, false);

        dataList = root.findViewById(R.id.dataList);



        titles.add("First Item");
        titles.add("Second Item");
        titles.add("Third Item");
        titles.add("Fourth Item");

        images.add(R.drawable.wk1);
        images.add(R.drawable.wk1);
        images.add(R.drawable.wk1);
        images.add(R.drawable.wk1);

        exercisesAdapter = new ExercisesAdapter(getContext(),titles,images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(exercisesAdapter);//set adapter to recyclerview

        return root;
    }
}