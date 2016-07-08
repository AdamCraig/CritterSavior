package com.epicodus.supremeinvention.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.supremeinvention.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.zipCodeEditText) EditText mZipCodeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mSearchButton) {
            String location = mZipCodeEditText.getText().toString();
            Intent intent = new Intent(SearchActivity.this, SearchResultsActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
    }
}
