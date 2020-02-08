package com.smartvillage.astagfirullah.activity.editor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.smartvillage.astagfirullah.R;
import com.smartvillage.astagfirullah.api.ApiInterface;

public class EditorJadwalPosyanduActivity extends AppCompatActivity implements EditorView {

    public static final String TANGGAL_POSYANDU = "jadwal posyandu"  ;
    public static final String WAKTU_POSYANDU = "WAKTU POSYANDU";
    public static final String ID_JADWAL_POSYANDU = "ID jadwal posyandu";
    public static final String ID = "ID";
    EditText etJadwalPosyandu, et_waktuyandu;
    ProgressDialog progressDialog;
    ApiInterface apiInterface;
    EditorPresenter presenter;

    int id;
    String tanggalPosyandu, waktuyandu;
    Menu actionMenu;
    Spinner spinnerTempatPosyandu;
    int idTempatPosyandu = 0;

    private String[] tempatPosyandu = {
            "Haurkuning I",
            "Haurkuning II",
            "Haurkuning III",
            "Haurkuning IV",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_jadwal_posyandu);

        getSupportActionBar().setTitle("Input Jadwal Posyandu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etJadwalPosyandu = findViewById(R.id.tanggal_posyandu);
        et_waktuyandu = findViewById(R.id.waktu_posyandu);
        spinnerTempatPosyandu = findViewById(R.id.spinner_tempat_posyandu);

        // inisialiasi Array Adapter dengan memasukkan string array di atas
        @SuppressLint("ResourceType") final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, tempatPosyandu);

        // mengeset Array Adapter tersebut ke Spinner
        spinnerTempatPosyandu.setClickable(false);
        spinnerTempatPosyandu.setEnabled(false);
        spinnerTempatPosyandu.setAdapter(adapter);

        // mengeset listener untuk mengetahui saat item dipilih
        spinnerTempatPosyandu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // memunculkan toast + value Spinner yang dipilih (diambil dari adapter)
//                Toast.makeText(EditorJadwalPosyanduActivity.this, "Selected position: "+i+ adapter.getItem(i), Toast.LENGTH_SHORT).show();
                idTempatPosyandu = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //PROGRESS DIALOG
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menunggu...");

        presenter = new EditorPresenter(this);

        Intent intent = getIntent();
        id = intent.getIntExtra(ID,0);
        tanggalPosyandu = intent.getStringExtra(TANGGAL_POSYANDU);
        waktuyandu = intent.getStringExtra(WAKTU_POSYANDU);
        idTempatPosyandu = intent.getIntExtra(ID_JADWAL_POSYANDU, 0);

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

        String tanggalPosyandu = etJadwalPosyandu.getText().toString().trim();
        Log.d("testme", "onOptionsItemSelected: "+tanggalPosyandu);
        String waktuyandu = et_waktuyandu.getText().toString().trim();

        switch (item.getItemId()) {
            case R.id.simpanJadwalPosyandu:
                //SIMPAN
                if (tanggalPosyandu.isEmpty()) {
                    etJadwalPosyandu.setError("Masukkan Jadwal Bidan");
                } else if (waktuyandu.isEmpty()) {
                    et_waktuyandu.setError("Masukkan Waktu Yandu");
                } else {
                    presenter.simpanJadwalPosyandu(tanggalPosyandu, waktuyandu, idTempatPosyandu);
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
                if (tanggalPosyandu.isEmpty()) {
                    etJadwalPosyandu.setError("Masukkan Jadwal Bidan");
                } else if (waktuyandu.isEmpty()) {
                    et_waktuyandu.setError("Masukkan Jadwal Bidan");
                } else {
                    presenter.updateJadwalPosyandu(id, tanggalPosyandu, waktuyandu, idTempatPosyandu);
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
        progressDialog.dismiss();
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
            etJadwalPosyandu.setText(tanggalPosyandu);
            et_waktuyandu.setText(waktuyandu);
            spinnerTempatPosyandu.setSelection(idTempatPosyandu - 1);

            getSupportActionBar().setTitle("Update Jadwal Posyandu");
            readMode();

        } else {
            editMode();
        }
    }

    private void editMode() {
        etJadwalPosyandu.setFocusableInTouchMode(true);
        et_waktuyandu.setFocusableInTouchMode(true);
        spinnerTempatPosyandu.setEnabled(true);
        spinnerTempatPosyandu.setClickable(true);
    }

    private void readMode() {
        etJadwalPosyandu.setFocusableInTouchMode(false);
        et_waktuyandu.setFocusableInTouchMode(false);
        etJadwalPosyandu.setFocusable(false);
        et_waktuyandu.setFocusable(false);
        spinnerTempatPosyandu.setEnabled(false);
        spinnerTempatPosyandu.setClickable(false);
    }
}
