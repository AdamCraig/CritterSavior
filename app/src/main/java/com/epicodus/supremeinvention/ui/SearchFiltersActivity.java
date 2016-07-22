package com.epicodus.supremeinvention.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.epicodus.supremeinvention.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchFiltersActivity extends AppCompatActivity {

    private String[] speciesList = { "All Animals", "Dog"};

    @Bind(R.id.sizeSpinner) Spinner mSizeSpinner;
    @Bind(R.id.breedSpinner) Spinner mBreedSpinner;
    @Bind(R.id.sexSpinner) Spinner mSexSpinner;
    @Bind(R.id.ageSpinner) Spinner mAgeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filters);
        ButterKnife.bind(this);

        ArrayAdapter<String> sizeSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, speciesList);
        sizeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSizeSpinner.setAdapter(sizeSpinnerAdapter);

        ArrayAdapter<String> breedSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, speciesList);
        breedSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBreedSpinner.setAdapter(breedSpinnerAdapter);

        ArrayAdapter<String> sexSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, speciesList);
        sexSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSexSpinner.setAdapter(sexSpinnerAdapter);

        ArrayAdapter<String> ageSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, speciesList);
        ageSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAgeSpinner.setAdapter(ageSpinnerAdapter);

    }
}
