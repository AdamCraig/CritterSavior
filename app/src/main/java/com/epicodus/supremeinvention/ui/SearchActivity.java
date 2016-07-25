package com.epicodus.supremeinvention.ui;

import android.content.Intent;
import android.content.SharedPreferences;
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
    @Bind(R.id.sizeSpinner) Spinner mSpeciesSpinner;

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
                intent.putExtra("size", "Any Size");
                intent.putExtra("breed", "Any Breed");
                intent.putExtra("sex", "Any Sex");
                intent.putExtra("age", "Any Age");
                startActivity(intent);
            } else {
                Intent intent = new Intent(SearchActivity.this, SearchFiltersActivity.class);
                intent.putExtra("location", location);
                intent.putExtra("species", species);
                startActivity(intent);
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
