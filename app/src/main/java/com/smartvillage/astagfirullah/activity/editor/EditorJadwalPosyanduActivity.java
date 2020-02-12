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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.smartvillage.astagfirullah.R;
import com.smartvillage.astagfirullah.activity.main.SessionManager;
import com.smartvillage.astagfirullah.commons.DatePickerFragment;
import com.smartvillage.astagfirullah.commons.TimePickerFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class EditorJadwalPosyanduActivity extends AppCompatActivity implements EditorView,
        DatePickerFragment.DialogDateListener, TimePickerFragment.DialogTimeListener,
        View.OnClickListener
{

    public static final String TANGGAL_POSYANDU = "jadwal posyandu";
    public static final String WAKTU_POSYANDU = "WAKTU POSYANDU";
    public static final String ID_JADWAL_POSYANDU = "ID jadwal posyandu";
    public static final String ID = "ID";
    private static final String TIME_PICKER = "TimePicker";
    private final String DATE_PICKER_TAG = "DatePicker";
    private TextView tvJadwalPosyandu, tvwaktuyandu;
    private ProgressDialog progressDialog;
    private EditorPresenter presenter;
    private ImageView ivDate, ivTime;
    private String getId;
    private SessionManager sessionManager;
    private int id;
    private String tanggalPosyandu, waktuyandu;
    private Menu actionMenu;
    private Spinner spinnerTempatPosyandu;
    private int idTempatPosyandu = 0;

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

        tvJadwalPosyandu = findViewById(R.id.tanggal_posyandu);
        tvJadwalPosyandu.setOnClickListener(this::onClick);
        tvwaktuyandu = findViewById(R.id.waktu_posyandu);
        tvwaktuyandu.setOnClickListener(this::onClick);
        spinnerTempatPosyandu = findViewById(R.id.spinner_tempat_posyandu);
        ivDate = findViewById(R.id.iv_date);
        ivDate.setOnClickListener(this::onClick);
        ivTime = findViewById(R.id.iv_time);
        ivTime.setOnClickListener(this::onClick);

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
        id = intent.getIntExtra(ID, 0);
        tanggalPosyandu = intent.getStringExtra(TANGGAL_POSYANDU);
        waktuyandu = intent.getStringExtra(WAKTU_POSYANDU);
        idTempatPosyandu = intent.getIntExtra(ID_JADWAL_POSYANDU, 0);

        setDataFromIntentExtra();
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.NAMA);
        if (getId.equals("Warga")){
            getSupportActionBar().setTitle("Detail Jadwal Posyandu");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editor_jadwalposyandu, menu);
        actionMenu = menu;
        if (getId.equals("Warga")) {
            actionMenu.findItem(R.id.editJadwalPosyandu).setVisible(false);
            actionMenu.findItem(R.id.deleteJadwalPosyandu).setVisible(false);
            actionMenu.findItem(R.id.simpanJadwalPosyandu).setVisible(false);
            actionMenu.findItem(R.id.updateJadwalPosyandu).setVisible(false);
        }else if (getId.equals("Linma")) {
            actionMenu.findItem(R.id.editJadwalPosyandu).setVisible(false);
            actionMenu.findItem(R.id.deleteJadwalPosyandu).setVisible(false);
            actionMenu.findItem(R.id.simpanJadwalPosyandu).setVisible(false);
            actionMenu.findItem(R.id.updateJadwalPosyandu).setVisible(false);
        }else if (id != 0) {
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

        String tanggalPosyandu = tvJadwalPosyandu.getText().toString().trim();
        Log.d("testme", "onOptionsItemSelected: " + tanggalPosyandu);
        String waktuyandu = tvwaktuyandu.getText().toString().trim();

        switch (item.getItemId()) {
            case R.id.simpanJadwalPosyandu:
                //SIMPAN
                if (tanggalPosyandu.isEmpty()) {
                    tvJadwalPosyandu.setError("Masukkan Jadwal Bidan");
                } else if (waktuyandu.isEmpty()) {
                    tvwaktuyandu.setError("Masukkan Waktu Yandu");
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
                    tvJadwalPosyandu.setError("Masukkan Jadwal Bidan");
                } else if (waktuyandu.isEmpty()) {
                    tvwaktuyandu.setError("Masukkan Jadwal Bidan");
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
        Toast.makeText(EditorJadwalPosyanduActivity.this, message,
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
            tvJadwalPosyandu.setText(tanggalPosyandu);
            tvwaktuyandu.setText(waktuyandu);
            spinnerTempatPosyandu.setSelection(idTempatPosyandu - 1);

            getSupportActionBar().setTitle("Update Jadwal Posyandu");
            readMode();

        } else {
            editMode();
        }
    }

    private void editMode() {
        ivTime.setEnabled(true);
        ivDate.setEnabled(true);
        tvJadwalPosyandu.setFocusableInTouchMode(true);
        tvJadwalPosyandu.setEnabled(true);
        tvwaktuyandu.setEnabled(true);
        tvwaktuyandu.setFocusableInTouchMode(true);
        spinnerTempatPosyandu.setEnabled(true);
        spinnerTempatPosyandu.setClickable(true);
    }

    private void readMode() {
        ivTime.setEnabled(false);
        ivDate.setEnabled(false);
        tvJadwalPosyandu.setEnabled(false);
        tvwaktuyandu.setEnabled(false);
        tvJadwalPosyandu.setFocusableInTouchMode(false);
        tvwaktuyandu.setFocusableInTouchMode(false);
        tvJadwalPosyandu.setFocusable(false);
        tvwaktuyandu.setFocusable(false);
        spinnerTempatPosyandu.setEnabled(false);
        spinnerTempatPosyandu.setClickable(false);
    }

    @Override
    public void onDialogDateSet(String tag, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        tvJadwalPosyandu.setText(dateFormat.format(calendar.getTime()));

    }

    @Override
    public void onDialogTimeSet(String tag, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        switch (tag) {
            case TIME_PICKER:
                tvwaktuyandu.setText(dateFormat.format(calendar.getTime()));
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tanggal_posyandu:
            case R.id.iv_date:
                openDatePicker();
                break;
            case R.id.waktu_posyandu:
            case R.id.iv_time:
                openTimePicker();
                break;
        }

    }

    void openDatePicker() {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), DATE_PICKER_TAG);

    }

    void openTimePicker() {
        TimePickerFragment timePickerFragmentOne = new TimePickerFragment();
        timePickerFragmentOne.show(getSupportFragmentManager(), TIME_PICKER);

    }
}
