package com.smartvillage.astagfirullah.activity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.smartvillage.astagfirullah.R;
import com.smartvillage.astagfirullah.activity.editor.EditorRiwayatSakitActivity;
import com.smartvillage.astagfirullah.model.Profil;
import com.smartvillage.astagfirullah.model.RiwayatSakit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfilActivity extends AppCompatActivity implements MainViewProfil{

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    private SessionManager sessionManager;
    private String nik, id;
    MainPresenterProfil presenter;
    MainAdapterProfil adapter;
    MainAdapterProfil.ItemClickListener itemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        getSupportActionBar().setTitle("Profil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapterProfil(this, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = findViewById(R.id.swiperefreshlayout);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        nik = user.get(sessionManager.NIK);
        id = user.get(sessionManager.NAMA);
        presenter = new MainPresenterProfil(this);
        presenter.getData(nik);

        swipeRefreshLayout.setOnRefreshListener(
                () -> presenter.getData(nik)
        );
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
    public void onGetResult(List<Profil> profilList) {
        Log.d("TAG", "onGetResult: "+profilList);
        adapter.setProfilList(profilList);

    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
