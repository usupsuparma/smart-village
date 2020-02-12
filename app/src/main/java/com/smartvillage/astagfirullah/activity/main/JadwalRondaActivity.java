package com.smartvillage.astagfirullah.activity.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smartvillage.astagfirullah.R;
import com.smartvillage.astagfirullah.activity.editor.EditorJadwalPosyanduActivity;
import com.smartvillage.astagfirullah.activity.editor.EditorJadwalRondaActivity;
import com.smartvillage.astagfirullah.model.JadwalRonda;

import java.util.HashMap;
import java.util.List;

public class JadwalRondaActivity extends AppCompatActivity implements MainViewJadwalRonda, MainAdapterJadwalRonda.ItemClickListener {

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;

    FloatingActionButton addjadwalronda;
    RecyclerView recyclerView;
    private SessionManager sessionManager;
    private String id;
    SwipeRefreshLayout swipeRefreshLayout;

    MainPresenterJadwalRonda presenter;
    MainAdapterJadwalRonda adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_ronda);

        getSupportActionBar().setTitle("Jadwal Ronda");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        swipeRefreshLayout = findViewById(R.id.swiperefreshlayout);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapterJadwalRonda(this, this::onItemClick);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        addjadwalronda = findViewById(R.id.addjadwalronda);
        addjadwalronda.setOnClickListener(view ->
                startActivityForResult(new Intent(this, EditorJadwalRondaActivity.class), INTENT_ADD)
        );

        presenter = new MainPresenterJadwalRonda(this);
        presenter.getData();

        swipeRefreshLayout.setOnRefreshListener(
                () -> presenter.getData()
        );
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        id = user.get(sessionManager.NAMA);
        if(id.equals("Admin")){
            addjadwalronda.show();
        }else if(id.equals("Linmas")) {
            addjadwalronda.show();
        }else{
            addjadwalronda.hide();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INTENT_ADD && resultCode == RESULT_OK) {
            presenter.getData(); //reload data
        } else if (requestCode == INTENT_EDIT && resultCode == RESULT_OK) {
            presenter.getData(); //reload data
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
    public void onGetResult(List<JadwalRonda> jadwalRondaList) {
       adapter.setJadwalRondaList(jadwalRondaList);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(JadwalRonda jadwalRonda, int position) {
        int id = jadwalRonda.getId();
        String namapetugas = jadwalRonda.getNamapetugas();
        String jadwalpetugas = jadwalRonda.getJadwalpetugas();
        String nik = jadwalRonda.getNik();
        int idHari = jadwalRonda.getIdHari();

        Intent intent = new Intent(this, EditorJadwalRondaActivity.class);

        intent.putExtra(EditorJadwalRondaActivity.ID, id);
        intent.putExtra(EditorJadwalRondaActivity.NAMA_PETUGAS, namapetugas);
        intent.putExtra(EditorJadwalRondaActivity.JADWAL_PETUGAS, jadwalpetugas);
        intent.putExtra(EditorJadwalRondaActivity.NIK, nik);
        intent.putExtra(EditorJadwalRondaActivity.ID_HARI, idHari);
        startActivityForResult(intent, INTENT_EDIT);
    }
}
