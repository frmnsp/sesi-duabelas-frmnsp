package com.odenktools.aplikasikuliah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //@TODO(11) TAMBAHKAN INITIALISASI WIDGET

        //@TODO(12) BUAT PERMODELAN CLASS UNTUK DESERIALIZE DATA DARI RESTFUL (GUNAKAN www.jsonschema2pojo.org)

        //@TODO(13) BUAT METHOD untuk memanggil Activity BeritaListActivity
    }

    //@TODO(14) BUAT METHOD postBerita() untuk insert data ke REST API
}
