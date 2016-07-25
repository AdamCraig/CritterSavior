package com.epicodus.supremeinvention.services;

import android.util.Log;

import com.epicodus.supremeinvention.Constants;
import com.epicodus.supremeinvention.models.Pet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PetService {
    public static final String TAG = PetService.class.getSimpleName();

    public static void findPetsByLocation(String location, String species, String size, String breed, String sex, String age, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        String url;

        if (species.equals("all")) {
            url = Constants.PET_BASE_URL + "pet.find?format=json&key=" + Constants.PET_CONSUMER_KEY + "&location=" + location;
        } else {
            url = Constants.PET_BASE_URL + "pet.find?format=json&key=" + Constants.PET_CONSUMER_KEY + "&location=" + location + "&animal=" + species;

            if (!size.equals("Any Size")) {
                url += ("&size=" + size);
            }
            if (!breed.equals("Any Breed")) {
                url += ("&breed=" + breed);
            }
            if (!sex.equals("Any Sex")) {
                url += ("&sex=" + sex);
            }
            if (!age.equals("Any Age")) {
                url += ("&age=" + age);
            }
        }

        Log.v("URL", url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public static void getBreedList(String species, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        String url = Constants.PET_BASE_URL + "breed.list?format=json&key=" + Constants.PET_CONSUMER_KEY + "&animal=" + species;

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public String trim(String stringToTrim) {
        return stringToTrim.substring(7, stringToTrim.length() - 2);
    }

    public ArrayList<Pet> processResults(Response response) {
        ArrayList<Pet> pets = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject petListJSON = new JSONObject(jsonData);
                JSONArray petsJSON = petListJSON.getJSONObject("petfinder").getJSONObject("pets").getJSONArray("pet");
                for (int i = 0; i < petsJSON.length(); i++) {
                    JSONObject petJSON = petsJSON.getJSONObject(i);
                    String name = petJSON.getJSONObject("name").getString("$t");
                    String id = petJSON.getJSONObject("id").getString("$t");
                    String species = petJSON.getJSONObject("animal").getString("$t");
                    String imageUrl = petJSON.getJSONObject("media")
                            .getJSONObject("photos")
                            .getJSONArray("photo")
                            .getJSONObject(2)
                            .getString("$t");
                    String email = petJSON.getJSONObject("contact")
                            .getJSONObject("email")
                            .getString("$t");

//                    Check if a phone exists, set number as dummy if not
                    String phone;
                    try {
                        phone = petJSON.getJSONObject("contact")
                                .getJSONObject("phone")
                                .getString("$t");
                    } catch (JSONException e) {
                        phone = "555-555-5555";
                    }


//                    Check if a description exists, output alternate string if not
                    String description;
                    try {
                        description = petJSON.getJSONObject("description")
                                .getString("$t");
                    } catch (JSONException e) {
                        description = "No description available.";
                    }

//                    Reformat size from single letter to readable string
                    String size = petJSON.getJSONObject("size").getString("$t");
                    if (size.equals("S")) {
                        size = "Small";
                    } else if (size.equals("M")) {
                        size = "Medium";
                    } else if (size.equals("L")) {
                        size = "Large";
                    } else if (size.equals("XL")) {
                        size = "Extra Large";
                    } else {
                        size = "N/A";
                    }

//                    Reformat sex from single letter to readable string
                    String sex = petJSON.getJSONObject("sex").getString("$t");
                    if (sex.equals("F")) {
                        sex = "Female";
                    } else if (sex.equals("M")) {
                        sex = "Male";
                    } else {
                        sex = "N/A";
                    }

                    String age = petJSON.getJSONObject("age").getString("$t");

//                    Check if the pet has multiple listed breeds, and if so, display just the first one
                    String breed;
                    try {
                        breed = petJSON.getJSONObject("breeds")
                                .getJSONObject("breed")
                                .getString("$t");
                    } catch (JSONException e) {
                        breed = petJSON.getJSONObject("breeds")
                                .getJSONArray("breed")
                                .getJSONObject(0)
                                .getString("$t");
                    }

                    Pet pet = new Pet(name, id, species, imageUrl, email, phone, description, size, sex, age, breed);
                    pets.add(pet);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pets;
    }

    public ArrayList<String> processBreedResults(Response response) {
        ArrayList<String> breeds = new ArrayList<>();

        try {
            String jsonData = response.body().string();

            if (response.isSuccessful()) {
                JSONObject breedListJSON = new JSONObject(jsonData);
                JSONArray breedsJSON = breedListJSON.getJSONObject("petfinder").getJSONObject("breeds").getJSONArray("breed");
                for (int i = 0; i < breedsJSON.length(); i++) {
                    JSONObject breedJSON = breedsJSON.getJSONObject(i);
                    String breed = breedJSON.getString("$t");

                    breeds.add(breed);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return breeds;
    }
}
