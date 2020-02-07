package com.smartvillage.astagfirullah.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smartvillage.astagfirullah.R;
import com.smartvillage.astagfirullah.activity.editor.EditorRiwayatSakitActivity;
import com.smartvillage.astagfirullah.model.RiwayatSakit;

import java.util.HashMap;
import java.util.List;

public class RiwayatSakitActivity extends AppCompatActivity implements MainViewRiwayatSakit, MainAdapterRiwayatSakit.ItemClickListener {

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;
    private SessionManager sessionManager;
    private String nik, id;

    private FloatingActionButton addriwayatsakit;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private MainPresenterRiwayatSakit presenter;
    private MainAdapterRiwayatSakit adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_sakit);

        getSupportActionBar().setTitle("Riwayat Sakit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        swipeRefreshLayout = findViewById(R.id.swiperefreshlayout);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapterRiwayatSakit(this, this);
        recyclerView.setAdapter(adapter);
        addriwayatsakit = findViewById(R.id.addriwayatsakit);
        addriwayatsakit.setOnClickListener(view ->
                startActivityForResult(new Intent(this, EditorRiwayatSakitActivity.class), INTENT_ADD)
        );
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        nik = user.get(sessionManager.NIK);
        id = user.get(sessionManager.NAMA);
        if(id.equals("Admin")){
            addriwayatsakit.show();
        }else{
            addriwayatsakit.hide();
        }
        presenter = new MainPresenterRiwayatSakit(this);
        presenter.getData(nik);

        swipeRefreshLayout.setOnRefreshListener(
                () -> presenter.getData(nik)
        );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INTENT_ADD && resultCode == RESULT_OK) {
            presenter.getData(nik); //reload data
        } else if (requestCode == INTENT_EDIT && resultCode == RESULT_OK) {
            presenter.getData(nik); //reload data
        }
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
    public void onGetResult(List<RiwayatSakit> riwayatSakitList) {
        adapter.setRiwayatSakitList(riwayatSakitList);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View view, RiwayatSakit riwayatSakit) {
        String namapasien = riwayatSakit.getNamapasien();
        String tanggalsakit = riwayatSakit.getTanggalsakit();
        String penyakitpasien = riwayatSakit.getPenyakitpasien();
        Intent intent = new Intent(this, EditorRiwayatSakitActivity.class);
        intent.putExtra(EditorRiwayatSakitActivity.ID, riwayatSakit.getId());
        intent.putExtra(EditorRiwayatSakitActivity.NAMA_PASIEN, namapasien);
        intent.putExtra(EditorRiwayatSakitActivity.PENYAKIT_PASIEN, penyakitpasien);
        intent.putExtra(EditorRiwayatSakitActivity.TANGGAL_SAKIT, tanggalsakit);
        startActivityForResult(intent, INTENT_EDIT);
    }
}
