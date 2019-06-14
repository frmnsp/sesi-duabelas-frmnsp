package com.odenktools.aplikasikuliah;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BeritaListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_list);
        //@TODO(15) TAMBAHKAN INITIALISASI WIDGET
    }

    //@TODO(16) BUAT METHOD getListBerita() untuk mengambil data dari REST API

    //@TODO(18) BUAT CLASS BARU BeritaRecyclerAdapter yang extends ke RecyclerView.Adapter (SESI6)

    //@TODO(19) PADA METHOD getListBerita() INSERT DATA DARI REST API TERSEBUT PADA ADAPTER (SESI6)
}
