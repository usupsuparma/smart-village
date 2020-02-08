package com.smartvillage.astagfirullah.activity.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smartvillage.astagfirullah.R;
import com.smartvillage.astagfirullah.activity.editor.EditorJadwalPosyanduActivity;
import com.smartvillage.astagfirullah.model.JadwalPosyandu;

import java.util.List;

public class JadwalPosyanduActivity extends AppCompatActivity implements MainViewJadwalPosyandu, MainAdapterJadwalPosyandu.ItemClickListener {

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;

    private FloatingActionButton addjadwalposyandu;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private MainPresenterJadwalPosyandu presenter;
    private MainAdapterJadwalPosyandu adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_posyandu);

        getSupportActionBar().setTitle("Jadwal Posyandu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        swipeRefreshLayout = findViewById(R.id.swiperefreshlayout);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new MainAdapterJadwalPosyandu(this::onItemClick);
        recyclerView.setAdapter(adapter);

        addjadwalposyandu = findViewById(R.id.addjadwalposyandu);
        addjadwalposyandu.setOnClickListener(view ->
                startActivityForResult(new Intent(this, EditorJadwalPosyanduActivity.class), INTENT_ADD)
        );

        presenter = new MainPresenterJadwalPosyandu(this);
        presenter.getData();

        swipeRefreshLayout.setOnRefreshListener(
                () -> presenter.getData()
        );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INTENT_ADD && resultCode == RESULT_OK) {
            presenter.getData(); //reload data
        } else if (requestCode == INTENT_EDIT && resultCode == RESULT_OK) {
            Log.d("testme", "onActivityResult: dari edit");
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
    public void onGetResult(List<JadwalPosyandu> jadwalPosyanduList) {
        Log.d("testme", "onGetResult: " + jadwalPosyanduList.size());
        adapter.setJadwalPosyanduList(jadwalPosyanduList);

    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(JadwalPosyandu jadwalPosyandu, int position) {
        int id = jadwalPosyandu.getId();
        String jadwalbidan = jadwalPosyandu.getTglYandu();
        String waktuyandu = jadwalPosyandu.getWaktuyandu();
        int idTempatPosyandu = jadwalPosyandu.getIdPosyandu();

        Intent intent = new Intent(this, EditorJadwalPosyanduActivity.class);

        intent.putExtra(EditorJadwalPosyanduActivity.ID, id);
        intent.putExtra(EditorJadwalPosyanduActivity.TANGGAL_POSYANDU, jadwalbidan);
        intent.putExtra(EditorJadwalPosyanduActivity.WAKTU_POSYANDU, waktuyandu);
        intent.putExtra(EditorJadwalPosyanduActivity.ID_JADWAL_POSYANDU, idTempatPosyandu);
        startActivityForResult(intent, INTENT_EDIT);
    }
}
