package com.epicodus.supremeinvention.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Pet {
    String mName;
    String mId;
    String mSpecies;
    String mImageUrl;
    String mEmail;
    String mPhone;
    String mDescription;
    String mSize;
    String mSex;
    String mAge;
    String mBreed;
    private String pushId;

    public Pet() {}

    public Pet(String name, String id, String species, String imageUrl, String email, String phone, String description, String size, String sex, String age, String breed) {
        this.mName = name;
        this.mId = id;
        this.mSpecies = species;
        this.mImageUrl = imageUrl;
        this.mEmail = email;
        this.mPhone = phone;
        this.mDescription = description;
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

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getDescription() {
        return mDescription;
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

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
