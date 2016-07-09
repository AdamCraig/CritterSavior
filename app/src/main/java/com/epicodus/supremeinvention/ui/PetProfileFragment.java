package com.epicodus.supremeinvention.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.supremeinvention.R;
import com.epicodus.supremeinvention.models.Pet;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PetProfileFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.petImageView) ImageView mImageLabel;
    @Bind(R.id.nameTextView) TextView mNameLabel;
    @Bind(R.id.breedTextView) TextView mBreedLabel;
    @Bind(R.id.sexTextView) TextView mSexLabel;
    @Bind(R.id.sizeTextView) TextView mSizeLabel;
    @Bind(R.id.descriptionTextView) TextView mDescriptionLabel;
    @Bind(R.id.ageTextView) TextView mAgeLabel;
    @Bind(R.id.adoptButton) Button mAdoptButton;
    @Bind(R.id.favoritePetButton) Button mFavoritePetButton;

    private Pet mPet;

    public static PetProfileFragment newInstance(Pet pet) {
        PetProfileFragment petProfileFragment = new PetProfileFragment();
        Bundle args = new Bundle();
        args.putParcelable("pet", Parcels.wrap(pet));
        petProfileFragment.setArguments(args);
        return petProfileFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPet = Parcels.unwrap(getArguments().getParcelable("pet"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet_profile, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mPet.getImageUrl()).into(mImageLabel);

        mAdoptButton.setOnClickListener(this);

        mNameLabel.setText(mPet.getName());
        mBreedLabel.setText(mPet.getBreed());
        mSexLabel.setText(mPet.getSex());
        mSizeLabel.setText("Size: " + mPet.getSize());
        mDescriptionLabel.setText(mPet.getDescription());
        mAgeLabel.setText("Age: " + mPet.getAge());

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mAdoptButton) {

//            Call The Shelter
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mPet.getPhone()));
            startActivity(phoneIntent);

//            Send An Email
//            ArrayList<String> addresses = new ArrayList<>();
//            addresses.add(mPet.getEmail());
//            Intent emailIntent = new Intent(Intent.ACTION_SEND);
//            emailIntent.putExtra(Intent.EXTRA_EMAIL, addresses);
//            startActivity(emailIntent);
        }
    }


}
