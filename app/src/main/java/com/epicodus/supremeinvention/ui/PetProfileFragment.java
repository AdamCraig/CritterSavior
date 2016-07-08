package com.epicodus.supremeinvention.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.supremeinvention.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetProfileFragment extends Fragment {


    public PetProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pet_profile, container, false);
    }

}
