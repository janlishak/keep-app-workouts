package com.janlishak.keepappworkouts.services.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static ImgurApi imgurApi;

    public static ImgurApi getImgurApi() {
        if (imgurApi == null) {
            imgurApi = new Retrofit.Builder()
                    .baseUrl("https://api.imgur.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ImgurApi.class);
        }
        return imgurApi;
    }
}
