package com.epicodus.supremeinvention;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchResultsActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.petsListView) ListView mPetsListView;

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
//        mPetsListView.setOnClickListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pets);
        mPetsListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
//        if (view == mPetsListView) {
//            Intent intent = new Intent(SearchResultsActivity.this, PetProfileActivity.class);
//            startActivity(intent);
//        }
    }
}
