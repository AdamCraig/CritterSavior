package com.epicodus.supremeinvention.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.epicodus.supremeinvention.Constants;
import com.epicodus.supremeinvention.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mLocationPreference;
    private String[] speciesList = { "All Animals", "Dog", "Cat", "Small Mammal", "Bird", "Horse", "Reptile", "Farm Animal", "Pig"};

    @Bind(R.id.nextStepButton) Button mNextStepButton;
    @Bind(R.id.zipCodeEditText) EditText mZipCodeEditText;
    @Bind(R.id.speciesSpinner) Spinner mSpeciesSpinner;
//    @Bind(R.id.optionalTextView) TextView mOptionalTextView;
//    @Bind(R.id.sizeSpinner) Spinner mSizeSpinner;
//    @Bind(R.id.sexSpinner) Spinner mSexSpinner;
//    @Bind(R.id.breedEditText) EditText mBreedEditText;
//    @Bind(R.id.ageEditText) EditText mAgeEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        ArrayAdapter<String> speciesSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, speciesList);
        speciesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpeciesSpinner.setAdapter(speciesSpinnerAdapter);

        mSpeciesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View selectedItemView, int position, long id) {
                if (mSpeciesSpinner.getSelectedItem().toString().equals("All Animals")) {
                    mNextStepButton.setText("Start Search");
                } else {
                    mNextStepButton.setText("Next Step");
                }
                Log.v("SPECIES SELECTED", adapterView.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // If a location preference exists in Shared Preferences, auto-fill it
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mLocationPreference = mSharedPreferences.getString(Constants.PREFERENCES_ZIP_KEY, null);
        if (mLocationPreference != null) {
            mZipCodeEditText.setText(mLocationPreference);
        }

        mNextStepButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mNextStepButton) {
            String location = mZipCodeEditText.getText().toString();

            addToSharedPreferences(location);

            String species = "all";
            String selectedSpecies = mSpeciesSpinner.getSelectedItem().toString();
            species = formatSpecies(selectedSpecies);

            if (species.equals("all")) {
                Intent intent = new Intent(SearchActivity.this, SearchResultsActivity.class);
                intent.putExtra("location", location);
                intent.putExtra("species", species);
                startActivity(intent);
            } else {

            }

        }
    }

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_ZIP_KEY, location).apply();
    }

    private String formatSpecies(String speciesSpinnerSelection) {
        switch (speciesSpinnerSelection) {
            case "All Animals": {
                return "all";
            }
            case "Dog": {
                return "dog";
            }
            case "Cat": {
                return "cat";
            }
            case "Small Mammal": {
                return "smallfurry";
            }
            case "Bird": {
                return "bird";
            }
            case "Horse": {
                return "horse";
            }
            case "Reptile": {
                return "reptile";
            }
            case "Farm Animal": {
                return "barnyard";
            }
            case "Pig": {
                return "pig";
            }
            default: {
                return "all";
            }
        }
    }

}
