package com.smartvillage.astagfirullah.activity.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.smartvillage.astagfirullah.R;

import java.util.HashMap;

public class Dashboard extends AppCompatActivity {

    CardView suhu, riwayatsakit, websitedesa, jadwalronda, jadwalposyandu, keadaandarurat, profil, keluar;
    String getId;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);

        keluar = findViewById(R.id.keluar);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
            }
        });

        suhu = findViewById(R.id.suhu);
        suhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, SuhuActivity.class);
                startActivity(i);
            }
        });

        riwayatsakit = findViewById(R.id.riwayatsakit);
        riwayatsakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, RiwayatSakitActivity.class);
                startActivity(i);
            }
        });

        jadwalronda = findViewById(R.id.jadwalronda);
        jadwalronda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, JadwalRondaActivity.class);
                startActivity(i);
            }
        });

        jadwalposyandu = findViewById(R.id.jadwalposyandu);
        jadwalposyandu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, JadwalPosyanduActivity.class);
                startActivity(i);
            }
        });

        keadaandarurat = findViewById(R.id.keadaandarurat);
        keadaandarurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, KeadaanDaruratActivity.class);
                startActivity(i);
            }
        });

        profil = findViewById(R.id.profil);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, ProfilActivity.class);
                startActivity(i);
            }
        });
    }

    //DIRECT WEBSITE DESA!
    public void websitedesa(View view) {
        Intent websitedesaIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.simdesapp.windstandrobotic.org"));
        startActivity(websitedesaIntent);
    }
}