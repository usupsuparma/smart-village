package com.smartvillage.astagfirullah.activity.editor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.smartvillage.astagfirullah.R;
import com.smartvillage.astagfirullah.api.ApiInterface;

import java.sql.Date;
import java.sql.Time;

public class EditorJadwalPosyanduActivity extends AppCompatActivity implements EditorView {

    EditText et_namabidan, et_jadwalbidan, et_waktuyandu;
    ProgressDialog progressDialog;
    ApiInterface apiInterface;
    EditorPresenter presenter;

    int id;
    String namabidan;
    String  jadwalbidan, waktuyandu;
    Menu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_jadwal_posyandu);

        getSupportActionBar().setTitle("Input Jadwal Posyandu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_namabidan = findViewById(R.id.namabidan);
        et_jadwalbidan = findViewById(R.id.jadwalbidan);
        et_waktuyandu = findViewById(R.id.waktuyandu);

        //PROGRESS DIALOG
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menunggu...");

        presenter = new EditorPresenter(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        namabidan = intent.getStringExtra("namabidan");
        jadwalbidan = intent.getStringExtra("jadwalbidan");
        waktuyandu = intent.getStringExtra("waktuyandu");

        setDataFromIntentExtra();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editor_jadwalposyandu, menu);
        actionMenu = menu;

        if (id != 0){
            actionMenu.findItem(R.id.editJadwalPosyandu).setVisible(true);
            actionMenu.findItem(R.id.deleteJadwalPosyandu).setVisible(true);
            actionMenu.findItem(R.id.simpanJadwalPosyandu).setVisible(false);
            actionMenu.findItem(R.id.updateJadwalPosyandu).setVisible(false);
        } else {
            actionMenu.findItem(R.id.editJadwalPosyandu).setVisible(false);
            actionMenu.findItem(R.id.deleteJadwalPosyandu).setVisible(false);
            actionMenu.findItem(R.id.simpanJadwalPosyandu).setVisible(true);
            actionMenu.findItem(R.id.updateJadwalPosyandu).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String namabidan = et_namabidan.getText().toString().trim();
        String jadwalbidan = et_jadwalbidan.getText().toString().trim();
        String waktuyandu = et_waktuyandu.getText().toString().trim();

        switch (item.getItemId()) {
            case R.id.simpanJadwalPosyandu:
                //SIMPAN

                if (namabidan.isEmpty()) {
                    et_namabidan.setError("Masukkan Nama Bidan");
                } else if (jadwalbidan.isEmpty()) {
                    et_jadwalbidan.setError("Masukkan Jadwal Bidan");
                } else if (waktuyandu.isEmpty()) {
                    et_waktuyandu.setError("Masukkan Waktu Yandu");
                } else {
                    presenter.simpanJadwalPosyandu(namabidan, jadwalbidan, waktuyandu);
                }
                return true;

            case R.id.editJadwalPosyandu:
                //EDIT
                editMode();
                actionMenu.findItem(R.id.editJadwalPosyandu).setVisible(false);
                actionMenu.findItem(R.id.deleteJadwalPosyandu).setVisible(false);
                actionMenu.findItem(R.id.simpanJadwalPosyandu).setVisible(false);
                actionMenu.findItem(R.id.updateJadwalPosyandu).setVisible(true);

                return true;

            case R.id.updateJadwalPosyandu:
                //UPDATE
                if (namabidan.isEmpty()) {
                    et_namabidan.setError("Masukkan Nama Bidan");
                } else if (jadwalbidan.isEmpty()) {
                    et_jadwalbidan.setError("Masukkan Jadwal Bidan");
                } else if (waktuyandu.isEmpty()) {
                    et_waktuyandu.setError("Masukkan Jadwal Bidan");
                } else {
                    presenter.updateJadwalPosyandu(id, namabidan, jadwalbidan, waktuyandu);
                }
                return true;

            case R.id.deleteJadwalPosyandu:
                //DELETE
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Hapus Jadwal Posyandu?");
                alertDialog.setNegativeButton("Ya",
                        (dialog, which) -> {
                            dialog.dismiss();
                            presenter.deleteJadwalPosyandu(id);
                        });

                alertDialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(EditorJadwalPosyanduActivity.this,message,
                Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish(); // Back to JadwalPosyanduActivity
    }

    @Override
    public void onRequestError(String message) {
            Toast.makeText(EditorJadwalPosyanduActivity.this, message,
                    Toast.LENGTH_SHORT).show();
    }

    private void setDataFromIntentExtra() {
        if (id != 0) {
            et_namabidan.setText(namabidan);
            et_jadwalbidan.setText(jadwalbidan);
            et_waktuyandu.setText(waktuyandu);

            getSupportActionBar().setTitle("Update Jadwal Posyandu");
            readMode();

        } else {
            editMode();
        }
    }

    private void editMode() {
        et_namabidan.setFocusableInTouchMode(true);
        et_jadwalbidan.setFocusableInTouchMode(true);
        et_waktuyandu.setFocusableInTouchMode(true);
    }

    private void readMode() {
        et_namabidan.setFocusableInTouchMode(false);
        et_jadwalbidan.setFocusableInTouchMode(false);
        et_waktuyandu.setFocusableInTouchMode(false);
        et_namabidan.setFocusable(false);
        et_jadwalbidan.setFocusable(false);
        et_waktuyandu.setFocusable(false);
    }
}
