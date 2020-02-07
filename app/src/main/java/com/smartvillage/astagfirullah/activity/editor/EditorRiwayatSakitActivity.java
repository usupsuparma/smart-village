package com.smartvillage.astagfirullah.activity.editor;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.smartvillage.astagfirullah.R;
import com.smartvillage.astagfirullah.activity.main.SessionManager;

import java.util.HashMap;

public class EditorRiwayatSakitActivity extends AppCompatActivity implements EditorView {
    public static final String ID = "id";
    public static final String NAMA_PASIEN = "nama_pasien";
    public static final String PENYAKIT_PASIEN = "penyakit_pasien";
    public static final String TANGGAL_SAKIT = "tanggalSakit";

    private EditText et_namapasien, et_penyakitpasien;
    private ProgressDialog progressDialog;
    private EditorPresenter presenter;
    private SessionManager sessionManager;
    private String getId;


    private int id;
    private String namapasien, penyakitpasien;
    private Menu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editorriwayatsakit);
        getSupportActionBar().setTitle("Input Riwayat Sakit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        et_namapasien = findViewById(R.id.namapasien);
        et_penyakitpasien = findViewById(R.id.penyakitpasien);

        //PROGRESS DIALOG
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menunggu...");

        presenter = new EditorPresenter(this);

        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra(ID, 0);
            namapasien = intent.getStringExtra(NAMA_PASIEN);
            penyakitpasien = intent.getStringExtra(PENYAKIT_PASIEN);
        }

        setDataFromIntentExtra();
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.NAMA);
        if (getId.equals("Warga")){
            getSupportActionBar().setTitle("Detail Riwayat Sakit");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editor_riwayatsakit,menu);
        actionMenu = menu;

        if (getId.equals("Warga")) {
            actionMenu.findItem(R.id.editRiwayatSakit).setVisible(false);
            actionMenu.findItem(R.id.deleteRiwayatSakit).setVisible(false);
            actionMenu.findItem(R.id.simpanRiwayatSakit).setVisible(false);
            actionMenu.findItem(R.id.updateRiwayatSakit).setVisible(false);
        }else if (id != 0){
                actionMenu.findItem(R.id.editRiwayatSakit).setVisible(true);
                actionMenu.findItem(R.id.deleteRiwayatSakit).setVisible(true);
                actionMenu.findItem(R.id.simpanRiwayatSakit).setVisible(false);
                actionMenu.findItem(R.id.updateRiwayatSakit).setVisible(false);
            } else {
                actionMenu.findItem(R.id.editRiwayatSakit).setVisible(false);
                actionMenu.findItem(R.id.deleteRiwayatSakit).setVisible(false);
                actionMenu.findItem(R.id.simpanRiwayatSakit).setVisible(true);
                actionMenu.findItem(R.id.updateRiwayatSakit).setVisible(false);
            }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String namapasien = et_namapasien.getText().toString().trim();
        String penyakitpasien = et_penyakitpasien.getText().toString().trim();

        switch (item.getItemId()) {
            case R.id.simpanRiwayatSakit:
                //SIMPAN
                if (namapasien.isEmpty()) {
                    et_namapasien.setError("Masukkan Nama Pasien");
                } else if (penyakitpasien.isEmpty()) {
                    et_penyakitpasien.setError("Masukkan Penyakit Pasien");
                } else {
                    presenter.simpanRiwayatSakit(namapasien, penyakitpasien);
                }
                return true;

            case R.id.editRiwayatSakit:
                //EDIT
                editMode();
                actionMenu.findItem(R.id.editRiwayatSakit).setVisible(false);
                actionMenu.findItem(R.id.deleteRiwayatSakit).setVisible(false);
                actionMenu.findItem(R.id.simpanRiwayatSakit).setVisible(false);
                actionMenu.findItem(R.id.updateRiwayatSakit).setVisible(true);

                return true;

            case R.id.updateRiwayatSakit:
                //UPDATE
                if (namapasien.isEmpty()) {
                    et_namapasien.setError("Masukkan Nama Pasien");
                } else if (penyakitpasien.isEmpty()) {
                    et_penyakitpasien.setError("Masukkan Penyakit Pasien");
                } else {
                    presenter.updateRiwayatSakit(id, namapasien, penyakitpasien);
                }
                return true;

            case R.id.deleteRiwayatSakit:
                //DELETE
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Hapus Riwayat Sakit?");
                alertDialog.setNegativeButton("Ya",
                        (dialog, which) -> {
                            dialog.dismiss();
                            presenter.deleteRiwayatSakit(id);
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
        Toast.makeText(EditorRiwayatSakitActivity.this, message,
            Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish(); // Back to RiwayatSakitActivity
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(EditorRiwayatSakitActivity.this, message,
            Toast.LENGTH_SHORT).show();
    }

    private void setDataFromIntentExtra() {
        if (id != 0) {
            et_namapasien.setText(namapasien);
            et_penyakitpasien.setText(penyakitpasien);
            getSupportActionBar().setTitle("Update Riwayat Sakit");
            readMode();
        } else {
            editMode();
        }
    }

    private void editMode() {
        et_namapasien.setFocusableInTouchMode(true);
        et_penyakitpasien.setFocusableInTouchMode(true);
    }

    private void readMode() {
        et_namapasien.setFocusableInTouchMode(false);
        et_penyakitpasien.setFocusableInTouchMode(false);
        et_namapasien.setFocusable(false);
        et_penyakitpasien.setFocusable(false);
    }
}