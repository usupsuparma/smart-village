package com.smartvillage.astagfirullah.activity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.smartvillage.astagfirullah.R;
import com.smartvillage.astagfirullah.activity.editor.EditorRiwayatSakitActivity;
import com.smartvillage.astagfirullah.model.Profil;
import com.smartvillage.astagfirullah.model.RiwayatSakit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfilActivity extends AppCompatActivity implements MainViewProfil, MainAdapterProfil.ItemClickListener{

    SwipeRefreshLayout swipeRefreshLayout;

    private SessionManager sessionManager;
    private String nik, id;
    MainPresenterProfil presenter;

    private TextView tvNik, nama, tanggalLahir, jenisKelamin, alamat, agama, status, pekerjaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        initView();

        getSupportActionBar().setTitle("Profil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        nik = user.get(sessionManager.NIK);
        Log.d("testme", "onCreate: "+nik);
        id = user.get(sessionManager.NAMA);
        presenter = new MainPresenterProfil(this);
        presenter.getData(nik);

        swipeRefreshLayout.setOnRefreshListener(
                () -> presenter.getData(nik)
        );
    }

    private void initView() {
        swipeRefreshLayout = findViewById(R.id.swiperefreshlayout);
        tvNik = findViewById(R.id.nik);
        nama = findViewById(R.id.nama);
        tanggalLahir = findViewById(R.id.tanggallahir);
        jenisKelamin = findViewById(R.id.jeniskelamin);
        alamat = findViewById(R.id.alamat);
        agama = findViewById(R.id.agama);
        status = findViewById(R.id.status);
        pekerjaan = findViewById(R.id.pekerjaan);
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<Profil> profils) {
        Profil profil = profils.get(0);
        Log.d("TAG", "onGetResult: "+profil);
        tvNik.setText(profil.getNik());
        nama.setText(profil.getNama());
        tanggalLahir.setText(profil.getTanggallahir());
        jenisKelamin.setText(profil.getJeniskelamin());
        alamat.setText(profil.getAlamat());
        agama.setText(profil.getAgama());
        status.setText(profil.getStatus());
        pekerjaan.setText(profil.getPekerjaan());

    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Profil profil) {

    }
}
