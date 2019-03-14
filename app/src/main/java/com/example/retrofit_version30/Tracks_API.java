package com.example.retrofit_version30;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Tracks_API {

    @GET("tracks")
    Call<List<Track>> getTracks();

    @POST()
    Call<Track> postTrack (@Body RequestBody requestBody);
}
