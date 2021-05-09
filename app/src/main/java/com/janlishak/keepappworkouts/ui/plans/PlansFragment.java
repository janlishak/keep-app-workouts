package com.janlishak.keepappworkouts.ui.plans;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.janlishak.keepappworkouts.PlanAdapter;
import com.janlishak.keepappworkouts.PlanModel;
import com.janlishak.keepappworkouts.R;

import java.util.ArrayList;

public class PlansFragment extends Fragment {

    private PlansViewModel plansViewModel;
    private RecyclerView courseRV;
    private ArrayList<PlanModel> PlanModelArrayList;

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        plansViewModel = new ViewModelProvider(this).get(PlansViewModel.class);
        View root = inflater.inflate(R.layout.fragment_plans, container, false);

        courseRV = root.findViewById(R.id.idRVCourse);

        // here we have created new array list and added data to it.
        PlanModelArrayList = new ArrayList<>();
        PlanModelArrayList.add(new PlanModel("DSA in Java", 4, R.drawable.wk1));
        PlanModelArrayList.add(new PlanModel("Java Course", 3, R.drawable.wk1));
        PlanModelArrayList.add(new PlanModel("C++ COurse", 4, R.drawable.wk1));
        PlanModelArrayList.add(new PlanModel("DSA in C++", 4, R.drawable.wk1));
        PlanModelArrayList.add(new PlanModel("Kotlin for Android", 4, R.drawable.wk1));
        PlanModelArrayList.add(new PlanModel("Java for Android", 4, R.drawable.wk1));
        PlanModelArrayList.add(new PlanModel("HTML and CSS", 4, R.drawable.wk1));

        // we are initializing our adapter class and passing our arraylist to it.
        PlanAdapter planAdapter = new PlanAdapter(getContext(), PlanModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(planAdapter);

        return root;
    }
}