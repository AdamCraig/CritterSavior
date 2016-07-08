package com.epicodus.supremeinvention.ui;


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

import butterknife.Bind;
import butterknife.ButterKnife;

public class PetProfileFragment extends Fragment {
    @Bind(R.id.petImageView) ImageView mImageLabel;
    @Bind(R.id.nameTextView) TextView mNameLabel;
    @Bind(R.id.petBreedTextView) TextView mBreedLabel;
    @Bind(R.id.petSexTextView) TextView mSexLabel;
    @Bind(R.id.petSizeTextView) TextView mSizeLabel;
    @Bind(R.id.descriptionTextView) TextView mDescriptionLabel;
    @Bind(R.id.ageTextView) TextView mAgeLabel;
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

        mNameLabel.setText(mPet.getName());
        mBreedLabel.setText(mPet.getBreed());
        mSexLabel.setText(mPet.getSex());
        mSizeLabel.setText("Size: " + mPet.getSize());
        mDescriptionLabel.setText(mPet.getDescription());
        mAgeLabel.setText("Age: " + mPet.getAge());

        return view;
    }


}
