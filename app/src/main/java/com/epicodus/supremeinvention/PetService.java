package com.epicodus.supremeinvention;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class PetService {

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
}
