package com.epicodus.supremeinvention.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.supremeinvention.Constants;
import com.epicodus.supremeinvention.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mLocationPreference;

    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.zipCodeEditText) EditText mZipCodeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mLocationPreference = mSharedPreferences.getString(Constants.PREFERENCES_ZIP_KEY, null);

        if (mLocationPreference != null) {
            mZipCodeEditText.setText(mLocationPreference);
        }

        mSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mSearchButton) {
            String location = mZipCodeEditText.getText().toString();

            addToSharedPreferences(location);

            Intent intent = new Intent(SearchActivity.this, SearchResultsActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
    }

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_ZIP_KEY, location).apply();
    }
}
