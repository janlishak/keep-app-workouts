package com.janlishak.keepappworkouts.authentication_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.firebase.ui.auth.AuthUI;
import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.ui.main_activity.MainActivity;

import java.util.Arrays;
import java.util.List;

public class AuthenticationActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int RC_SIGN_IN = 420;
    private AuthenticationViewModel viewModel;

    private Button debugSkipButton;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        viewModel = new ViewModelProvider(this).get(AuthenticationViewModel.class);
        checkIfSignedIn();

        debugSkipButton = findViewById(R.id.debug_skip_login_button);
        loginButton = findViewById(R.id.sign_in_button);

        debugSkipButton.setOnClickListener(this::onClick);
        loginButton.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.debug_skip_login_button:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            handleSignInRequest(resultCode);
        }
    }

    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null)
                goToMainActivity();
        });
    }

    private void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void signIn(){
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.wk1)
                .build();

        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInRequest(int resultCode) {
        if (resultCode == RESULT_OK)
            goToMainActivity();
        else
            Toast.makeText(this, "SIGN IN CANCELLED", Toast.LENGTH_SHORT).show();
    }

}