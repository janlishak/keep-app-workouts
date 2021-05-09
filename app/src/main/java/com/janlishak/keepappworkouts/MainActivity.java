package com.janlishak.keepappworkouts;

import android.os.Bundle;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.janlishak.keepappworkouts.ui.exercises.ExercisesFragment;
import com.janlishak.keepappworkouts.ui.profile.ProfileFragment;
import com.janlishak.keepappworkouts.ui.programs.ProgramsFragment;
import com.janlishak.keepappworkouts.ui.workouts.WorkoutsFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private ImageView toolbarImageView;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private BottomNavigationView navigation;
    private AppBarLayout appBarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set the layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find views
        fab = findViewById(R.id.fab);
        toolbarImageView = findViewById(R.id.toolbar_image);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        toolbar = findViewById(R.id.toolbar);
        navigation = findViewById(R.id.navigation);
        appBarLayout = findViewById(R.id.app_bar);

        //
        setSupportActionBar(toolbar);

        //change the title
        toolbar.post(() -> collapsingToolbarLayout.setTitle(navigation.getMenu().getItem(0).getTitle()));

        //setup bottom menu
        navigation.setOnNavigationItemSelectedListener(item -> {
            collapsingToolbarLayout.setTitle(item.getTitle());
            Fragment newFragment;
            switch (item.getItemId()){
                case R.id.navigation_programs:
                    newFragment = new ProgramsFragment();
                    appBarLayout.setExpanded(false);
                    break;
                case R.id.navigation_workouts:
                    newFragment = new WorkoutsFragment();
                    appBarLayout.setExpanded(true);
                    break;
                case R.id.navigation_exercises:
                    newFragment = new ExercisesFragment();
                    appBarLayout.setExpanded(false);
                    break;
                case R.id.navigation_profile:
                    newFragment = new ProfileFragment();
                    appBarLayout.setExpanded(false);
                    break;
                default:
                    newFragment = new Fragment();
            }


            openFragment(newFragment);
            return true;
        });

        //setup floating button
        fab.setOnClickListener(v -> {
            Snackbar.make(v, "hey", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });

        //make the image background darker
        toolbarImageView.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.teal_700), android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}