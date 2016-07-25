package com.epicodus.supremeinvention.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.supremeinvention.R;
import com.epicodus.supremeinvention.adapters.PetListAdapter;
import com.epicodus.supremeinvention.models.Pet;
import com.epicodus.supremeinvention.services.PetService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchResultsActivity extends AppCompatActivity {
    public static final String TAG = SearchResultsActivity.class.getSimpleName();

    @Bind(R.id.petsListRecyclerView) RecyclerView mPetsListRecyclerView;
    @Bind(R.id.noResultsImageView) ImageView mNoResultsImageView;
    @Bind(R.id.noResultsTextView) TextView mNoResultsTextView;

    private PetListAdapter mAdapter;

    public ArrayList<Pet> mPets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        String species = intent.getStringExtra("species");

        getPetsByLocation(location, species);
    }

    private void getPetsByLocation(String location, String species) {
        final PetService petService = new PetService();
        petService.findPetsByLocation(location, species, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mPets = petService.processResults(response);

                SearchResultsActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new PetListAdapter(getApplicationContext(), mPets);
                        mPetsListRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchResultsActivity.this);
                        mPetsListRecyclerView.setLayoutManager(layoutManager);
                        mPetsListRecyclerView.setHasFixedSize(true);

                        if (mPets.isEmpty()) {
                            mNoResultsImageView.setVisibility(View.VISIBLE);
                            mNoResultsTextView.setVisibility(View.VISIBLE);
                        }
                    }
                });

            }
        });
    }
}
