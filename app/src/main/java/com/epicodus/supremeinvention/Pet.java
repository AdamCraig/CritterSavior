package com.epicodus.supremeinvention;

import org.parceler.Parcel;

import java.util.ArrayList;

public class Pet {
    String mName;
    String mId;
    String mSpecies;
    String mImageUrl;
    String mSize;
    String mSex;
    String mAge;
    String mBreed;

    public Pet() {}

    public Pet(String name, String id, String species, String imageUrl, String size, String sex, String age, String breed) {
        this.mName = name;
        this.mId = id;
        this.mSpecies = species;
        this.mImageUrl = imageUrl;
        this.mSize = size;
        this.mSex = sex;
        this.mAge = age;
        this.mBreed = breed;
    }

    public String getName() {
        return mName;
    }

    public String getId() {
        return mId;
    }

    public String getSpecies() {
        return mSpecies;
    }

    public String getSize() {
        return mSize;
    }

    public String getSex() {
        return mSex;
    }

    public String getAge() {
        return mAge;
    }

    public String getBreed() {
        return mBreed;
    }
}
