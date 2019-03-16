package com.example.retrofit_version30;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditTrack extends AppCompatActivity {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://147.83.7.203:8080/dsaApp/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Tracks_API tracks_api = retrofit.create((Tracks_API.class));

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);


        Button edit = findViewById(R.id.edit);
        EditText title_1 = findViewById(R.id.titulo_mostrar2);
        EditText cantante_1 = findViewById(R.id.singer_mostrar2);
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
        Toast toast = Toast.makeText(getApplicationContext(),
                id+"holaaa",
                Toast.LENGTH_SHORT);
        toast.show();
        final Track track = new Track(id,title_1.getText().toString(),cantante_1.getText().toString());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Void> call = tracks_api.putTrack(track);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()) {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Error: " + response.code(),
                                    Toast.LENGTH_SHORT);
                            toast.show();
                            return;
                        }
                        Intent myIntent = new Intent(EditTrack.this, MainActivity.class);
                        startActivity(myIntent);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "La comunicación con el servidor ha fallado.",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }
        });


    }
}
