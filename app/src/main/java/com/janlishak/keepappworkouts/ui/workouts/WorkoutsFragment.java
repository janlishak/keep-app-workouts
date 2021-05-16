package com.janlishak.keepappworkouts.ui.workouts;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.model.Session;


public class WorkoutsFragment extends Fragment {

    private WorkoutsViewModel viewModel;
    private View root;

    private RecyclerView recyclerView;
    private SessionsAdapter sessionsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(WorkoutsViewModel.class);
        root = inflater.inflate(R.layout.fragment_workouts, container, false);
        setHasOptionsMenu(true);

        //Binds Toolbar color
        viewModel.getDeleteMode().observe(getViewLifecycleOwner(), deleteMode -> getActivity().findViewById(R.id.toolbar).setBackground(deleteMode? new ColorDrawable(0xFFFF6666):new ColorDrawable(0xFF006666)));

        //RecycleView
        recyclerView = root.findViewById(R.id.SessionsRecyclerView);
        sessionsAdapter = new SessionsAdapter();
        sessionsAdapter.setListener(createRecycleViewOnClickListener());

        viewModel.getSessions().observe(getViewLifecycleOwner(), Session -> sessionsAdapter.setData(Session));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(sessionsAdapter);

        //active plan card
        CardView activeCard = root.findViewById(R.id.active_plan_card);
        TextView activeCardName = activeCard.findViewById(R.id.plan_card_name_text_view);
        TextView activeCardDescription = activeCard.findViewById(R.id.plan_card_description_text_view);

        viewModel.getActivePlan().observe(getViewLifecycleOwner(), plan -> {
            if(plan != null){
            activeCardName.setText(plan.getName());
            activeCardDescription.setText(plan.getDescription());}
        });

        return root;
    }

    private View.OnClickListener createRecycleViewOnClickListener(){
        return view -> {
            Session session = sessionsAdapter.getSession(recyclerView.getChildLayoutPosition(view));
            if (viewModel.getDeleteMode().getValue()) {
                viewModel.remove(session);
            } else {
                Bundle bundle = new Bundle();
                bundle.putSerializable("session", session);
                bundle.putSerializable("plan", viewModel.getActivePlan().getValue());
                Navigation.findNavController(root).navigate(R.id.navigation_session_exercise_browser, bundle);
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
                Navigation.findNavController(root).navigate(R.id.navigation_session_creation);
                return true;
            case R.id.action_delete:
                viewModel.toggleDeleteMode();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}