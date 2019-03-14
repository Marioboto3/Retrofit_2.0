package com.example.retrofit_version30;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoTrack extends AppCompatActivity {
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

        TextView idsong = findViewById(R.id.id_mostrar);
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

        }
}
