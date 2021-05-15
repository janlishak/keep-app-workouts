package com.janlishak.keepappworkouts.ui.plan_creation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.janlishak.keepappworkouts.R;

public class PlanCreationFragment extends Fragment {
    private View root;
    private PlanCreationViewModel planCreationViewModel;
    private EditText name;
    private EditText description;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        planCreationViewModel = new ViewModelProvider(this).get(PlanCreationViewModel.class);
        root = inflater.inflate(R.layout.fragment_plan_creation, container, false);
        setHasOptionsMenu(true);

        name = root.findViewById(R.id.plan_creation_name);
        description = root.findViewById(R.id.plan_creation_description);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.creation_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_create:
                planCreationViewModel.getName().setValue(name.getText().toString());
                planCreationViewModel.getDescription().setValue(description.getText().toString());
                planCreationViewModel.createPlan();
                ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(root.getWindowToken(), 0);
                Navigation.findNavController(root).popBackStack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
