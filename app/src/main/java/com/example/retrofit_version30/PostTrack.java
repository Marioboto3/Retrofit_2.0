package com.example.retrofit_version30;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PostTrack extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://147.83.7.203:8080/dsaApp/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Tracks_API tracks_api = retrofit.create((Tracks_API.class));

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_main);

        Button post = findViewById(R.id.post);
        final EditText titulo = findViewById(R.id.titulo_escrito);
        final EditText cantante = findViewById(R.id.singer_escrito);
        final EditText id = findViewById(R.id.id_escrito);


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Track track = new Track(id.getText().toString(),cantante.getText().toString(),titulo.getText().toString());

                Call<Track> call = tracks_api.postTrack(track);
                call.enqueue(new Callback<Track>() {
                    @Override
                    public void onResponse(Call<Track> call, Response<Track> response) {
                        if (!response.isSuccessful()) {

                            Toast toast =
                                    Toast.makeText(getApplicationContext(),
                                            response.code(), Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else {
                            Intent myIntent = new Intent(PostTrack.this, MainActivity.class);
                            startActivity(myIntent);
                        }
                    }
                    @Override
                    public void onFailure(Call<Track> call, Throwable t) {

                    }
                });

            }
        });
    }
}
