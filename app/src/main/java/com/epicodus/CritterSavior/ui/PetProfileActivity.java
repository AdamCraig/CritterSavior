package com.epicodus.CritterSavior.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.epicodus.CritterSavior.R;
import com.epicodus.CritterSavior.adapters.PetPagerAdapter;
import com.epicodus.CritterSavior.models.Pet;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PetProfileActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.viewPager) ViewPager mViewPager;
    private PetPagerAdapter adapterViewPager;
    ArrayList<Pet> mPets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_profile);
        ButterKnife.bind(this);

        //TODO: Implement hiding Favorite Pet button if profile is accessed from MyFavoritesActivity
//        Intent intent = getIntent();
//        String origin = intent.getStringExtra("origin");
//
//        if (origin.equals("MyFavoritesActivity")) {
//            findViewById(R.id.favoritePetButton).setVisibility(View.INVISIBLE);
//        }

        mPets = Parcels.unwrap(getIntent().getParcelableExtra("pets"));
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));

        adapterViewPager = new PetPagerAdapter(getSupportFragmentManager(), mPets);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);

    }

    @Override
    public void onClick(View view) {

    }
}
