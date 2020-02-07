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
import com.smartvillage.astagfirullah.model.JadwalPosyandu;

import java.util.List;

public class JadwalPosyanduActivity extends AppCompatActivity implements MainViewJadwalPosyandu{

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;

    FloatingActionButton addjadwalposyandu;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    MainPresenterJadwalPosyandu presenter;
    MainAdapterJadwalPosyandu adapter;
    MainAdapterJadwalPosyandu.ItemClickListener itemClickListener;

    List<JadwalPosyandu> jadwalPosyanduList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_posyandu);

        getSupportActionBar().setTitle("Jadwal Posyandu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        swipeRefreshLayout = findViewById(R.id.swiperefreshlayout);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addjadwalposyandu = findViewById(R.id.addjadwalposyandu);
        addjadwalposyandu.setOnClickListener(view ->
                startActivityForResult(new Intent(this, EditorJadwalPosyanduActivity.class), INTENT_ADD)
        );

        presenter = new MainPresenterJadwalPosyandu(this);
        presenter.getData();

        swipeRefreshLayout.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemClickListener = ((view, position) -> {
            int id = jadwalPosyanduList.get(position).getId();
            String namabidan = jadwalPosyanduList.get(position).getNamabidan();
            String jadwalbidan = jadwalPosyanduList.get(position).getJadwalbidan();
            String waktuyandu = jadwalPosyanduList.get(position).getWaktuyandu();

            Intent intent = new Intent(this, EditorJadwalPosyanduActivity.class);

            intent.putExtra("id", id);
            intent.putExtra("namabidan", namabidan);
            intent.putExtra("jadwalbidan", jadwalbidan);
            intent.putExtra("waktuyandu", waktuyandu);
            startActivityForResult(intent, INTENT_EDIT);
        });
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
    public void onGetResult(List<JadwalPosyandu> jadwalPosyanduListl) {
        adapter = new MainAdapterJadwalPosyandu(this, jadwalPosyanduListl, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        jadwalPosyanduList = jadwalPosyanduListl;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
