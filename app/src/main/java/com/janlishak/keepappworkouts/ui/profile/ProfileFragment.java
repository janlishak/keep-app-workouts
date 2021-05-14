package com.janlishak.keepappworkouts.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.firebase.ui.auth.AuthUI;
import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.activities.NotificationsActivity;
import com.janlishak.keepappworkouts.authentication_activity.AuthenticationActivity;
import com.janlishak.keepappworkouts.ui.main_activity.MainActivity;


public class ProfileFragment extends Fragment implements View.OnClickListener{
    private View root;
    private ProfileViewModel profileViewModel;
    private Button logOutButton;
    private ProfileViewModel viewModel;
    private TextView displayNameTextView;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        root = inflater.inflate(R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        displayNameTextView = root.findViewById(R.id.user_name_textview);
        checkIfSignedIn();
        return root;
    }

    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                displayNameTextView.setText(user.getDisplayName());
            } else{
                startActivity(new Intent(getActivity(), AuthenticationActivity.class));
                getActivity().finish();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
        logOutButton = root.findViewById(R.id.log_out_button);
        logOutButton.setOnClickListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_profile_settings:
                intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_profile_edit:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.action_profile_notifications:
                intent = new Intent(getActivity(), NotificationsActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.log_out_button:
                AuthUI.getInstance().signOut(getContext());
                break;
        }
    }
}