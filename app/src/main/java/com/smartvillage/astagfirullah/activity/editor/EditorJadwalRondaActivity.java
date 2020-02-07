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
import com.smartvillage.astagfirullah.api.ApiClient;
import com.smartvillage.astagfirullah.api.ApiInterface;
import com.smartvillage.astagfirullah.model.JadwalRonda;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorJadwalRondaActivity extends AppCompatActivity implements EditorView{

    EditText et_namapetugas, et_jadwalpetugas;
    ProgressDialog progressDialog;
    ApiInterface apiInterface;
    EditorPresenter presenter;

    int id;
    String namapetugas, jadwalpetugas;
    Menu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_jadwal_ronda);

        getSupportActionBar().setTitle("Input Jadwal Ronda");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_namapetugas = findViewById(R.id.namapetugas);
        et_jadwalpetugas = findViewById(R.id.jadwalpetugas);

        //PROGRESS DIALOG
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menunggu...");

        presenter = new EditorPresenter(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        namapetugas = intent.getStringExtra("namapetugas");
        jadwalpetugas = intent.getStringExtra("jadwalpetugas");

        setDataFromIntentExtra();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editor_jadwalronda, menu);
        actionMenu = menu;

        if (id != 0){
            actionMenu.findItem(R.id.editJadwalRonda).setVisible(true);
            actionMenu.findItem(R.id.deleteJadwalRonda).setVisible(true);
            actionMenu.findItem(R.id.simpanJadwalRonda).setVisible(false);
            actionMenu.findItem(R.id.updateJadwalRonda).setVisible(false);
        } else {
            actionMenu.findItem(R.id.editJadwalRonda).setVisible(false);
            actionMenu.findItem(R.id.deleteJadwalRonda).setVisible(false);
            actionMenu.findItem(R.id.simpanJadwalRonda).setVisible(true);
            actionMenu.findItem(R.id.updateJadwalRonda).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String namapetugas = et_namapetugas.getText().toString().trim();
        String jadwalpetugas = et_jadwalpetugas.getText().toString().trim();

        switch (item.getItemId()) {
            case R.id.simpanJadwalRonda:
                //SIMPAN

                if (namapetugas.isEmpty()) {
                    et_namapetugas.setError("Masukkan Nama Petugas");
                } else if (jadwalpetugas.isEmpty()) {
                    et_jadwalpetugas.setError("Masukkan Jadwal Petugas");
                } else {
                    presenter.simpanJadwalRonda(namapetugas, jadwalpetugas);
                }
                return true;

            case R.id.editJadwalRonda:
                //EDIT
                editMode();
                actionMenu.findItem(R.id.editJadwalRonda).setVisible(false);
                actionMenu.findItem(R.id.deleteJadwalRonda).setVisible(false);
                actionMenu.findItem(R.id.simpanJadwalRonda).setVisible(false);
                actionMenu.findItem(R.id.updateJadwalRonda).setVisible(true);

                return true;

            case R.id.updateJadwalRonda:
                //UPDATE
                if (namapetugas.isEmpty()) {
                    et_namapetugas.setError("Masukkan Nama Petugas");
                } else if (jadwalpetugas.isEmpty()) {
                    et_jadwalpetugas.setError("Masukkan Jadwal Petugas");
                } else {
                    presenter.updateRiwayatSakit(id, namapetugas, jadwalpetugas);
                }
                return true;

            case R.id.deleteJadwalRonda:
                //DELETE
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Hapus Jadwal Ronda?");
                alertDialog.setNegativeButton("Ya",
                        (dialog, which) -> {
                            dialog.dismiss();
                            presenter.deleteJadwalRonda(id);
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
        Toast.makeText(EditorJadwalRondaActivity.this,message,
                Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish(); // Back to JadwalRondaActivity
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(EditorJadwalRondaActivity.this, message,
                Toast.LENGTH_SHORT).show();

    }

    private void setDataFromIntentExtra() {

        if (id != 0) {
            et_namapetugas.setText(namapetugas);
            et_jadwalpetugas.setText(jadwalpetugas);

            getSupportActionBar().setTitle("Update Jadwal Ronda");
            readMode();

        } else {
            editMode();
        }

    }

    private void editMode() {
        et_namapetugas.setFocusableInTouchMode(true);
        et_jadwalpetugas.setFocusableInTouchMode(true);
    }

    private void readMode() {
        et_namapetugas.setFocusableInTouchMode(false);
        et_jadwalpetugas.setFocusableInTouchMode(false);
        et_namapetugas.setFocusable(false);
        et_jadwalpetugas.setFocusable(false);
    }

}