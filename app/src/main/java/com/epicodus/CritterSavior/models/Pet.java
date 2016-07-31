package com.epicodus.CritterSavior.models;

import org.parceler.Parcel;

@Parcel
public class Pet {
    String name;
    String id;
    String species;
    String imageUrl;
    String email;
    String phone;
    String description;
    String size;
    String sex;
    String age;
    String breed;
    private String pushId;

    public Pet() {}

    public Pet(String name, String id, String species, String imageUrl, String email, String phone, String description, String size, String sex, String age, String breed) {
        this.name = name;
        this.id = id;
        this.species = species;
        this.imageUrl = imageUrl;
        this.email = email;
        this.phone = phone;
        this.description = description;
        this.size = size;
        this.sex = sex;
        this.age = age;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public String getSize() {
        return size;
    }

    public String getSex() {
        return sex;
    }

    public String getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
