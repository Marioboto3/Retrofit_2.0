package com.example.retrofit_version30;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Tracks_API {

    @GET("tracks")
    Call<List<Track>> getTracks();

}
