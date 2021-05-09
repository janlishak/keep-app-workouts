package com.janlishak.keepappworkouts;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    private final Toolbar toolbar;
    public BottomNavItemSelectedListener(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        toolbar.setTitle(item.getTitle());
        return false;
    }
}
