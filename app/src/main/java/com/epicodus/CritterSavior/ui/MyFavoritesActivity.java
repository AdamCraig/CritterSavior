package com.epicodus.CritterSavior.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.CritterSavior.Constants;
import com.epicodus.CritterSavior.R;
import com.epicodus.CritterSavior.adapters.FirebasePetViewHolder;
import com.epicodus.CritterSavior.models.Pet;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyFavoritesActivity extends AppCompatActivity {
    private DatabaseReference mPetReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private ArrayList<Pet> favoritePets = new ArrayList<>();

    @Bind(R.id.petsListRecyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.noResultsImageView) ImageView mNoResultsImageView;
    @Bind(R.id.noResultsTextView) TextView mNoResultsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mPetReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_FAVORITE_PETS)
                .child(uid);

        mPetReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    favoritePets.add(snapshot.getValue(Pet.class));
                }

                if (favoritePets.isEmpty()) {
                    mNoResultsImageView.setVisibility(View.VISIBLE);
                    mNoResultsTextView.setText("Your favorites list is empty! Head back to the search and add some critters!");
                    mNoResultsTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Pet, FirebasePetViewHolder>
                (Pet.class, R.layout.pet_list_item, FirebasePetViewHolder.class,
                        mPetReference) {

            @Override
            protected void populateViewHolder(FirebasePetViewHolder viewHolder,
                                              Pet model, int position) {
                viewHolder.bindPet(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
