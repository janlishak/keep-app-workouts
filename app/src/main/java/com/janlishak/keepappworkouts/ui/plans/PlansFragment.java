package com.janlishak.keepappworkouts.ui.plans;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.janlishak.keepappworkouts.model.Plan;
import com.janlishak.keepappworkouts.R;

import java.util.ArrayList;

public class PlansFragment extends Fragment {

    private PlansViewModel plansViewModel;
    private RecyclerView courseRV;
    private ArrayList<Plan> planArrayList;

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        plansViewModel = new ViewModelProvider(this).get(PlansViewModel.class);
        View root = inflater.inflate(R.layout.fragment_plans, container, false);

        courseRV = root.findViewById(R.id.idRVCourse);

        // here we have created new array list and added data to it.
        planArrayList = new ArrayList<>();
        planArrayList.add(new Plan("DSA in Java", 4, R.drawable.wk1));
        planArrayList.add(new Plan("Java Course", 3, R.drawable.wk1));
        planArrayList.add(new Plan("C++ COurse", 4, R.drawable.wk1));
        planArrayList.add(new Plan("DSA in C++", 4, R.drawable.wk1));
        planArrayList.add(new Plan("Kotlin for Android", 4, R.drawable.wk1));
        planArrayList.add(new Plan("Java for Android", 4, R.drawable.wk1));
        planArrayList.add(new Plan("HTML and CSS", 4, R.drawable.wk1));

        // we are initializing our adapter class and passing our arraylist to it.
        PlanAdapter planAdapter = new PlanAdapter(getContext(), planArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(planAdapter);

        return root;
    }
}