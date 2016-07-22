package com.epicodus.supremeinvention.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.supremeinvention.Constants;
import com.epicodus.supremeinvention.R;
import com.epicodus.supremeinvention.models.Pet;
import com.epicodus.supremeinvention.ui.PetProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebasePetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebasePetViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindPet(Pet pet) {
        ImageView petImageView = (ImageView) mView.findViewById(R.id.petImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.nameTextView);
        TextView petBreedTextView = (TextView) mView.findViewById(R.id.petBreedTextView);
        TextView petSexTextView = (TextView) mView.findViewById(R.id.petSexTextView);
        TextView petSizeTextView = (TextView) mView.findViewById(R.id.petSizeTextView);

        Picasso.with(mContext).load(pet.getImageUrl()).into(petImageView);

        nameTextView.setText(pet.getName());
        petBreedTextView.setText(pet.getBreed());
        petSexTextView.setText(pet.getSex());
        petSizeTextView.setText(pet.getSize());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Pet> pets = new ArrayList<>();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_FAVORITE_PETS)
                .child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    pets.add(snapshot.getValue(Pet.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, PetProfileActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("pets", Parcels.wrap(pets));
                intent.putExtra("origin", mContext.getClass().getSimpleName());
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
