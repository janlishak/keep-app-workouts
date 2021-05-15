package com.janlishak.keepappworkouts.ui.plans;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.model.ExerciseCollection;
import com.janlishak.keepappworkouts.model.Plan;

import java.util.List;

public class PlansFragment extends Fragment {

    private PlansViewModel viewModel;
    private View root;

    private RecyclerView recyclerView;
    private PlansAdapter plansAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(PlansViewModel.class);
        root = inflater.inflate(R.layout.fragment_plans, container, false);
        setHasOptionsMenu(true);

        //Binds Toolbar color
        viewModel.getDeleteMode().observe(getViewLifecycleOwner(), deleteMode -> getActivity().findViewById(R.id.toolbar).setBackground(deleteMode? new ColorDrawable(0xFFFF6666):new ColorDrawable(0xFF006666)));

        //RecycleView
        recyclerView = root.findViewById(R.id.plans_recycle_view);
        plansAdapter = new PlansAdapter();
        plansAdapter.setListener(createRecycleViewOnClickListener());

        viewModel.getPlans().observe(getViewLifecycleOwner(), plans -> plansAdapter.setData(plans));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(plansAdapter);

        return root;
    }

    private View.OnClickListener createRecycleViewOnClickListener(){
        return view -> {
            Plan plan = plansAdapter.getPlan(recyclerView.getChildLayoutPosition(view));
            if (viewModel.getDeleteMode().getValue()) {
                viewModel.remove(plan);
            } else {
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("exercise", exercise);
                Navigation.findNavController(root).navigate(R.id.navigation_exercise_browser);
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