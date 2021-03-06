package com.example.retrofit_version30;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<Track> listDatos = new ArrayList<>();
    RecyclerView recycler;
    RecyclerView.Adapter mAdapter;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.192.42.69:8080/dsaApp/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Tracks_API tracks_api = retrofit.create((Tracks_API.class));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, PostTrack.class);
                startActivity(myIntent);
            }
        });

        recycler = findViewById(R.id.my_recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycler.setHasFixedSize(true);

        Call<List<Track>> call = tracks_api.getTracks();
        call.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                if (!response.isSuccessful()) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Toast por defecto", Toast.LENGTH_SHORT);
                    toast1.show();
                    return;
                }
                Toast tostaito =
                        Toast.makeText(getApplicationContext(),
                                "Lista de tracks obtenida.", Toast.LENGTH_SHORT);
                tostaito.show();
                listDatos = response.body();

                mAdapter = new MyAdapter(listDatos, MainActivity.this);
                recycler.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
            }
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Toast onFailure", Toast.LENGTH_SHORT);

        });
    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }


