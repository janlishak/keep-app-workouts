package com.janlishak.keepappworkouts.ui.main_activity;

import android.os.Bundle;

import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.janlishak.keepappworkouts.R;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity{
    private NavController navController;
    private MaterialToolbar toolbar;
    private MainViewModel viewModel;

    @Override
    public boolean onSupportNavigateUp() { return navController.navigateUp() || super.onSupportNavigateUp(); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setContentView(R.layout.activity_main);
        setupToolbar();
        setupNavigation();
    }

    private void setupToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupNavigation(){
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_profile).build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            switch (destination.getId()){
                case R.id.navigation_exercise_browser:
                case R.id.navigation_exercise_details:
                case R.id.navigation_exercise_creation:
                case R.id.navigation_session_details:
                    toolbar.setVisibility(View.VISIBLE);
                    navView.setVisibility(View.GONE);
                    break;
                default:
                    toolbar.setVisibility(View.VISIBLE);
                    navView.setVisibility(View.VISIBLE);
            }
        });
    }
}