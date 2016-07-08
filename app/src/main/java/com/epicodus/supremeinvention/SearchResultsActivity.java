package com.epicodus.supremeinvention;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchResultsActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = SearchResultsActivity.class.getSimpleName();

    @Bind(R.id.petsListView) ListView mPetsListView;
    @Bind(R.id.petProfileButton) Button mPetProfileButton;

    private String[] pets = new String[] {
            "Fluffy",
            "Fido",
            "Frank",
            "Fred",
            "Fantom",
            "Floofy",
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);
        mPetProfileButton.setOnClickListener(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        getPetsByLocation(location);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pets);
        mPetsListView.setAdapter(adapter);
    }

    private void getPetsByLocation(String location) {
        final PetService petService = new PetService();
        petService.findPetsByLocation(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    @Override
    public void onClick(View view) {
        if (view == mPetProfileButton) {
            Intent intent = new Intent(SearchResultsActivity.this, PetProfileActivity.class);
            startActivity(intent);
        }
    }
}
