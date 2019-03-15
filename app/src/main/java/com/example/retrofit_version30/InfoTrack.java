package com.example.retrofit_version30;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoTrack extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://147.83.7.203:8080/dsaApp/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Tracks_API tracks_api = retrofit.create((Tracks_API.class));

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_track);

        TextView titulosong = findViewById(R.id.titulo_mostrar);
        String tit;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                tit= null;
            } else {
                tit=extras.getString("Title");
            }
        } else {
            tit =(String) savedInstanceState.getSerializable("Title");
        }
        titulosong.setText(tit);

        final TextView idsong = findViewById(R.id.id_mostrar);
        String id =  null;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                id= null;
            } else {
                id=extras.getString("Id");
            }
        } else {
            id =(String) savedInstanceState.getSerializable("Id");
        }
        idsong.setText(id);

        TextView singersong = findViewById(R.id.singer_mostrar);
        String sin =  null;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                sin= null;
            } else {
                sin=extras.getString("Singer");
            }
        } else {
            sin =(String) savedInstanceState.getSerializable("Singer");
        }
        singersong.setText(sin);

        Button next = findViewById(R.id.atras);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        Button delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Void> call = tracks_api.deleteTrack(idsong.getText().toString());
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast toast =
                                Toast.makeText(getApplicationContext(),
                                        "Se ha añadido correctamente.", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent myIntent = new Intent(InfoTrack.this, MainActivity.class);
                        startActivity(myIntent);
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }
        });

        }
}
