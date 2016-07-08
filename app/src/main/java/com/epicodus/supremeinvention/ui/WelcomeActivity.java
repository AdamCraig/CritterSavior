package com.epicodus.supremeinvention.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.supremeinvention.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.welcomeTitleTextView) TextView mWelcomeTitleTextView;
    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.preferencesButton) Button mPreferencesButton;
    @Bind(R.id.logoutButton) Button mLogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        mWelcomeTitleTextView.setText("Welcome, " + email + "!");
        mPreferencesButton.setOnClickListener(this);
        mSearchButton.setOnClickListener(this);
        mLogoutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mSearchButton) {
            Intent intent = new Intent(WelcomeActivity.this, SearchActivity.class);
            startActivity(intent);
        }
        if (view == mPreferencesButton) {
            Intent intent = new Intent(WelcomeActivity.this, MyPreferencesActivity.class);
            startActivity(intent);
        }
        if (view == mLogoutButton) {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
        }


    }
}
