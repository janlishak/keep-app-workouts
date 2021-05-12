package com.janlishak.keepappworkouts.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.janlishak.keepappworkouts.R;

public class AuthenticationActivity extends AppCompatActivity {
    Button debugSkipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        //find views
        debugSkipButton = findViewById(R.id.debug_skip_login_button);

        //listeners
        debugSkipButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}