package com.epicodus.supremeinvention;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.welcomeTitleTextView) TextView mWelcomeTitleTextView;
    @Bind(R.id.logoutButton) Button mLogoutButton;
    @Bind(R.id.preferencesButton) Button mPreferencesButton;
    @Bind(R.id.searchButton) Button mSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        mWelcomeTitleTextView.setText("Welcome, " + email + "!");
    }

    @Override
    public void onClick(View view) {
        if (view == mLogoutButton) {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if (view == mPreferencesButton) {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if (view == mSearchButton) {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
