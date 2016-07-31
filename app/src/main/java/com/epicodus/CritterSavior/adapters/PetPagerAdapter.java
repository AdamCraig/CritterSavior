package com.epicodus.CritterSavior.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.CritterSavior.models.Pet;
import com.epicodus.CritterSavior.ui.PetProfileFragment;

import java.util.ArrayList;

public class PetPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Pet> mPets;

    public PetPagerAdapter(FragmentManager fm, ArrayList<Pet> pets) {
        super(fm);
        mPets = pets;
    }

    @Override
    public Fragment getItem(int position) {
        return PetProfileFragment.newInstance(mPets.get(position));
    }

    @Override
    public int getCount() {
        return mPets.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPets.get(position).getName();
    }
}
