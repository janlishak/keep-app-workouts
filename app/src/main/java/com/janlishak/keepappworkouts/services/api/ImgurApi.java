package com.janlishak.keepappworkouts.services.api;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ImgurApi {
    @Headers("Authorization: Client-ID 4fd513e770aa0a0")
    @GET("3/image/{id}")
    Call<ImgurImageResponse> getImage(@Path("id") String id);

    @Headers("Authorization: Client-ID 4fd513e770aa0a0")
    @Multipart
    @POST("3/image")
    Call<ImgurImageResponse> uploadImageFile(@Part MultipartBody.Part image);
}