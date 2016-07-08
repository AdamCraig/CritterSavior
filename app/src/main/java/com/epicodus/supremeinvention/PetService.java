package com.epicodus.supremeinvention;

import android.util.Log;

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

    public static void findPetsByLocation(String location, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        String url = Constants.PET_BASE_URL + Constants.PET_CONSUMER_KEY + "&location=" + location;

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

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
                    String name = petJSON.getString("name");
                    String id = petJSON.getString("id");
                    String species = petJSON.getString("animal");
//                    String imageUrl = petJSON.getJSONObject("media")
//                            .getJSONObject("photos")
//                            .getJSONArray("photo")
//                            .get(2)
//                            .toString();
                    String imageUrl = "https://s-media-cache-ak0.pinimg.com/236x/a2/bd/97/a2bd97858694a7bbb46e6b875618a1e0.jpg";
                    Log.v("image URL", imageUrl);
                    String size = petJSON.getString("size");
                    String sex = petJSON.getString("sex");
                    String age = petJSON.getString("age");

                    String breed;
                    try {
                        breed = petJSON.getJSONObject("breeds")
                                .getJSONObject("breed")
                                .toString();
                    } catch (JSONException e) {
                        breed = petJSON.getJSONObject("breeds")
                                .getJSONArray("breed")
                                .get(0)
                                .toString();
                    }

                    Pet pet = new Pet(name, id, species, imageUrl, size, sex, age, breed);
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
}
