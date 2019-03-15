package com.example.retrofit_version30;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Tracks_API {

    @GET("tracks")
    Call<List<Track>> getTracks();

    @POST("tracks")
    Call<Track> postTrack (@Body Track track);

    @DELETE()
    Call<Void> deleteTrack (@Path("id") String id);
}
