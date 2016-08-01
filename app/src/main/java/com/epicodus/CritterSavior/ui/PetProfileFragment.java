package com.epicodus.CritterSavior.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.CritterSavior.Constants;
import com.epicodus.CritterSavior.R;
import com.epicodus.CritterSavior.models.Pet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

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

        Picasso.with(view.getContext())
                .load(mPet.getImageUrl())
                .into(mImageLabel);

        mAdoptButton.setOnClickListener(this);
        mFavoritePetButton.setOnClickListener(this);

        mNameLabel.setText(mPet.getName());
        mBreedLabel.setText(mPet.getBreed());
        mSexLabel.setText(mPet.getSex());
        mSizeLabel.setText(mPet.getSize());
        mDescriptionLabel.setText(mPet.getDescription());
        mAgeLabel.setText(mPet.getAge());

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
        if (v == mFavoritePetButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference favoritePetsRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_FAVORITE_PETS)
                    .child(uid);

            DatabaseReference pushRef = favoritePetsRef.push();
            String pushId = pushRef.getKey();
            mPet.setPushId(pushId);
            pushRef.setValue(mPet);

            Toast.makeText(getContext(), "Pet Saved To Favorites.", Toast.LENGTH_SHORT).show();
        }
    }


}
