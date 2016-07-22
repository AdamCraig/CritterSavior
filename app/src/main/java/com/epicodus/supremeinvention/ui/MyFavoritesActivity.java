package com.epicodus.supremeinvention.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.epicodus.supremeinvention.Constants;
import com.epicodus.supremeinvention.R;
import com.epicodus.supremeinvention.adapters.FirebasePetViewHolder;
import com.epicodus.supremeinvention.models.Pet;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyFavoritesActivity extends AppCompatActivity {
    private DatabaseReference mPetReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.petsListRecyclerView) RecyclerView mRecyclerView;

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
