package com.janlishak.keepappworkouts.ui.programs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.janlishak.keepappworkouts.R;

public class ProgramsFragment extends Fragment {

    private ProgramsViewModel programsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        programsViewModel = new ViewModelProvider(this).get(ProgramsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_programs, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        programsViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));
        return root;
    }
}