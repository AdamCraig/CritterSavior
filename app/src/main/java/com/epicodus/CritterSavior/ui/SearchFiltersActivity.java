package com.epicodus.CritterSavior.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.epicodus.CritterSavior.R;
import com.epicodus.CritterSavior.services.PetService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchFiltersActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<String> mBreeds = new ArrayList<>();
    private String[] speciesList = { "All Animals", "Dog"};
    private String[] sizeList = {"Any Size", "Small", "Medium", "Large", "Extra Large"};
    private String[] breedList = {"Any Breed"};
    private String[] sexList = {"Any Sex", "Female", "Male"};
    private String[] ageList = {"Any Age", "Baby", "Young", "Adult", "Senior"};


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

        mBreedSpinner.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
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

        getBreedList(species);
    }

    @Override
    public void onClick(View view) {
        if (view == mStartSearchButton) {

            Intent searchParametersIntent = getIntent();
            String location = searchParametersIntent.getStringExtra("location");
            String species = searchParametersIntent.getStringExtra("species");
            String size = formatSize(mSizeSpinner.getSelectedItem().toString());
            String breed = mBreedSpinner.getSelectedItem().toString();
            String sex = formatSex(mSexSpinner.getSelectedItem().toString());
            String age = mAgeSpinner.getSelectedItem().toString();

            Intent finalSearchIntent = new Intent(SearchFiltersActivity.this, SearchResultsActivity.class);
            finalSearchIntent.putExtra("location", location);
            finalSearchIntent.putExtra("species", species);
            finalSearchIntent.putExtra("size", size);
            finalSearchIntent.putExtra("breed", breed);
            finalSearchIntent.putExtra("sex", sex);
            finalSearchIntent.putExtra("age", age);
            startActivity(finalSearchIntent);
        }
    }

    private void getBreedList(String species) {
        final PetService petService = new PetService();
        petService.getBreedList(species, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                mBreeds.add(0, "Any Breed");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mBreeds = petService.processBreedResults(response);
                mBreeds.add(0, "Any Breed");
                Log.v("mBreeds", mBreeds + "");

                SearchFiltersActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mBreedSpinner.setAdapter(new ArrayAdapter<String>(SearchFiltersActivity.this, R.layout.spinner_item, mBreeds));
                        mBreedSpinner.setVisibility(View.VISIBLE);
                    }
                });

            }
        });
    }

    // formatting methods to match user input to API-accepted queries
    public String formatSize(String size) {
        if (size.equals("Small")) {
            return "S";
        } else if (size.equals("Medium")) {
            return "M";
        } else if (size.equals("Large")) {
            return "L";
        } else if (size.equals("Extra Large")) {
            return "XL";
        } else {
            return "Any Size";
        }
    }

    public String formatSex(String sex) {
        if (sex.equals("Female")) {
            return "F";
        } else if (sex.equals("Male")) {
            return "M";
        } else {
            return "Any Sex";
        }
    }
}
