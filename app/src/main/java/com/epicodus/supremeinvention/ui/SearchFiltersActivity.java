package com.epicodus.supremeinvention.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.epicodus.supremeinvention.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchFiltersActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] speciesList = { "All Animals", "Dog"};
    private String[] sizeList = {"Any", "Small", "Medium", "Large", "Extra Large"};
    private String[] breedList = {"Any"};
    private String[] sexList = {"Any", "Female", "Male"};
    private String[] ageList = {"Any", "Baby", "Young", "Adult", "Senior"};

    @Bind(R.id.sizeSpinner) Spinner mSizeSpinner;
    @Bind(R.id.breedSpinner) Spinner mBreedSpinner;
    @Bind(R.id.sexSpinner) Spinner mSexSpinner;
    @Bind(R.id.ageSpinner) Spinner mAgeSpinner;
    @Bind(R.id.startSearchButton) Button mStartSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filters);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        String species = intent.getStringExtra("species");

        ArrayAdapter<String> sizeSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, sizeList);
        sizeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSizeSpinner.setAdapter(sizeSpinnerAdapter);

        ArrayAdapter<String> breedSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, breedList);
        breedSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBreedSpinner.setAdapter(breedSpinnerAdapter);

        ArrayAdapter<String> sexSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, sexList);
        sexSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSexSpinner.setAdapter(sexSpinnerAdapter);

        ArrayAdapter<String> ageSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, ageList);
        ageSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAgeSpinner.setAdapter(ageSpinnerAdapter);

        mStartSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mStartSearchButton) {

            Intent searchParametersIntent = getIntent();
            String location = searchParametersIntent.getStringExtra("location");
            String species = searchParametersIntent.getStringExtra("species");

            Intent finalSearchIntent = new Intent(SearchFiltersActivity.this, SearchResultsActivity.class);
            finalSearchIntent.putExtra("location", location);
            finalSearchIntent.putExtra("species", species);
            startActivity(finalSearchIntent);
        }
    }
}
