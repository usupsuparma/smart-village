package com.smartvillage.astagfirullah.activity.editor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class EditorJadwalRondaActivity extends AppCompatActivity implements EditorView{


    public static final String NIK = "nik";
    public static final String ID = "id";
    public static final String NAMA_PETUGAS = "nama_petugas";
    public static final String JADWAL_PETUGAS = "JADWAL_PETUGAS";
    public static final String ID_HARI = "id_hari";
    private EditText et_namapetugas;
    private ProgressDialog progressDialog;
    private ApiInterface apiInterface;
    private EditorPresenter presenter;

    int id;
    private String nik;
    private String namapetugas, jadwalpetugas;
    private Menu actionMenu;
    private Spinner spinnerJadwalRonda;
    private int idHari = 0;

    private String[] jadwalRonda = {
            "Senin",
            "Selasa",
            "Rabu",
            "Kamis",
            "Jumat",
            "Sabtu",
            "Minggu"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_jadwal_ronda);

        getSupportActionBar().setTitle("Input Jadwal Ronda");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initDataSpinnerJadwalRonda();

        et_namapetugas = findViewById(R.id.namapetugas);

        //PROGRESS DIALOG
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menunggu...");

        presenter = new EditorPresenter(this);

        Intent intent = getIntent();
        id = intent.getIntExtra(ID,0);
        namapetugas = intent.getStringExtra(NAMA_PETUGAS);
        nik = intent.getStringExtra(NIK);
        idHari = intent.getIntExtra(ID_HARI, 0);


        setDataFromIntentExtra();
    }

    void initDataSpinnerJadwalRonda() {
        spinnerJadwalRonda = findViewById(R.id.sp_hari);
        // inisialiasi Array Adapter dengan memasukkan string array di atas
        @SuppressLint("ResourceType") final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, jadwalRonda);

        // mengeset Array Adapter tersebut ke Spinner
        spinnerJadwalRonda.setClickable(false);
        spinnerJadwalRonda.setEnabled(false);
        spinnerJadwalRonda.setAdapter(adapter);

        // mengeset listener untuk mengetahui saat item dipilih
        spinnerJadwalRonda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // memunculkan toast + value Spinner yang dipilih (diambil dari adapter)
//                Toast.makeText(EditorJadwalPosyanduActivity.this, "Selected position: "+i+ adapter.getItem(i), Toast.LENGTH_SHORT).show();
                idHari = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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

        switch (item.getItemId()) {
            case R.id.simpanJadwalRonda:
                //SIMPAN

                if (namapetugas.isEmpty()) {
                    et_namapetugas.setError("Masukkan Nama Petugas");
                } else {
                    presenter.simpanJadwalRonda(namapetugas, idHari);
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
                } else {
                    presenter.updateJadwalRonda(id, namapetugas, idHari);
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
            et_namapetugas.setText(nik);

            spinnerJadwalRonda.setSelection(idHari - 1);

            getSupportActionBar().setTitle("Update Jadwal Ronda");
            readMode();

        } else {
            editMode();
        }

    }

    private void editMode() {
        et_namapetugas.setFocusableInTouchMode(true);
        spinnerJadwalRonda.setEnabled(true);
        spinnerJadwalRonda.setClickable(true);
    }

    private void readMode() {
        et_namapetugas.setFocusableInTouchMode(false);
        et_namapetugas.setFocusable(false);
        spinnerJadwalRonda.setEnabled(true);
        spinnerJadwalRonda.setClickable(true);
    }

}