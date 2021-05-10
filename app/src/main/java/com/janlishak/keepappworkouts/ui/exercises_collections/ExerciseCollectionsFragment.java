package com.janlishak.keepappworkouts.ui.exercises_collections;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.activities.NotificationsActivity;
import com.janlishak.keepappworkouts.activities.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class ExerciseCollectionsFragment extends Fragment {

    private ExerciseCollectionsViewModel exerciseCollectionsViewModel;
    private View root;

    private RecyclerView dataList;
    List<String> titles = new ArrayList<>();
    List<Integer> images = new ArrayList<>();
    ExercisesCollectionAdapter exercisesCollectionAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        exerciseCollectionsViewModel = new ViewModelProvider(this).get(ExerciseCollectionsViewModel.class);
        root = inflater.inflate(R.layout.fragment_exercise_collections, container, false);
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
        inflater.inflate(R.menu.exercise_collections_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_delete_exercise_collection:
//                getActivity().getActionBar().setBackgroundDrawable();
//                root.setBackground(new ColorDrawable(0xFFFF6666));
                getActivity().findViewById(R.id.toolbar).setBackground(new ColorDrawable(0xFFFF6666));
                return true;

            case R.id.action_profile_edit:
                return true;

            case R.id.action_profile_notifications:
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}