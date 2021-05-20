package com.janlishak.keepappworkouts.services.api;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImgurRepository {

    private static ImgurRepository instance;

    private ImgurRepository() { }

    public static synchronized ImgurRepository getInstance() {
        if (instance == null) {
            instance = new ImgurRepository();
        }
        return instance;
    }

    public LiveData<Link> searchForImage(String imgurId) {
        MutableLiveData<Link> linkLiveData = new MutableLiveData<>();
        linkLiveData.setValue(new Link());

        ImgurApi imgurApi = ServiceGenerator.getImgurApi();
        Call<ImgurImageResponse> call = imgurApi.getPokemon(imgurId);
        call.enqueue(new Callback<ImgurImageResponse>() {
            @Override
            public void onResponse(Call<ImgurImageResponse> call, Response<ImgurImageResponse> response) {
                Log.i("Retrofit", response.toString());
                if (response.isSuccessful()) {
                    Log.i("Retrofit", "setting liveDataTo: " + response.body().getLink());
                    linkLiveData.setValue(response.body().getLink());
                }else {
                    linkLiveData.setValue(new Link(false, true, null));
                }
            }
            @Override public void onFailure(Call<ImgurImageResponse> call, Throwable t) {
                linkLiveData.setValue(new Link(false, true, null));
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
        return linkLiveData;
    }

    public MutableLiveData<Link> uploadImage(String fileUrl){
        File file = new File(fileUrl);
        Log.i("Retrofit", "insdie" );
        MutableLiveData<Link> linkLiveData = new MutableLiveData<>();
        linkLiveData.setValue(new Link());

        ImgurApi imgurApi = ServiceGenerator.getImgurApi();

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        Log.i("Retrofit", "insdie2" );
        Call<ImgurImageResponse> call = imgurApi.uploadFile2(body);


        Log.i("Retrofit", "insdie77" );
        call.enqueue(new Callback<ImgurImageResponse>() {
            @Override
            public void onResponse(Call<ImgurImageResponse> call, Response<ImgurImageResponse> response) {
                Log.i("Retrofit", "insdie3" );
                Log.i("Retrofit", response.toString());
                if (response.isSuccessful()) {
                    Log.i("Retrofit", "setting liveDataTo: " + response.body().getLink());
                    Log.i("Retrofit", "setting liveDataTo: " + response.body().toString());
                    linkLiveData.setValue(response.body().getLink());
                }else {
                    linkLiveData.setValue(new Link(false, true, null));
                }
            }
            @Override public void onFailure(Call<ImgurImageResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t.getMessage());
                linkLiveData.setValue(new Link(false, true, null));
            }
        });
        return linkLiveData;
    }
}
