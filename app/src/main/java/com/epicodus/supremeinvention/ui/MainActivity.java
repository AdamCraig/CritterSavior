package com.epicodus.supremeinvention.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.supremeinvention.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.emailEntryEditText) EditText mEmailEntryEditText;
    @Bind(R.id.passwordEntryEditText) EditText mPasswordEntryEditText;
    @Bind(R.id.loginButton) Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mLoginButton) {
            String email = mEmailEntryEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            intent.putExtra("email", email);
            startActivity(intent);
        }
    }
}