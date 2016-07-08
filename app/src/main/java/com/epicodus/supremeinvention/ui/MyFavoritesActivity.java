package com.epicodus.supremeinvention.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.supremeinvention.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyFavoritesActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.favoritesListView) ListView mFavoritesListView;
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
        setContentView(R.layout.activity_my_favorites);
        ButterKnife.bind(this);
        mPetProfileButton.setOnClickListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pets);
        mFavoritesListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view == mPetProfileButton) {
            Intent intent = new Intent(MyFavoritesActivity.this, PetProfileActivity.class);
            startActivity(intent);
        }
    }
}
