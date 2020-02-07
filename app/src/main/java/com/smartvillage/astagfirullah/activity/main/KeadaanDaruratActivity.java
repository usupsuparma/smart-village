package com.smartvillage.astagfirullah.activity.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.smartvillage.astagfirullah.R;
import com.smartvillage.astagfirullah.api.ApiClient;
import com.smartvillage.astagfirullah.api.ApiInterface;
import com.smartvillage.astagfirullah.model.KeadaanDarurat;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeadaanDaruratActivity extends AppCompatActivity {

    EditText et_pesandarurat;
    ProgressDialog progressDialog;
    ApiInterface apiInterface;
    private RadioGroup mRgStatus;
    SessionManager sessionManager;
    String getId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keadaan_darurat);
        mRgStatus = findViewById(R.id.rgStatus);

        getSupportActionBar().setTitle("Keadaan Darurat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_pesandarurat = findViewById(R.id.pesandarurat);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menunggu...");
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.NIK);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editor_keadaandarurat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.simpanPesanDarurat:

                String pesandarurat = et_pesandarurat.getText().toString().trim();

                if (pesandarurat.isEmpty()) {
                    et_pesandarurat.setError("Masukkan Pesan Darurat");
                } else {
                    simpanPesanDarurat(pesandarurat);
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void simpanPesanDarurat(final String pesandarurat) {
        int id = mRgStatus.getCheckedRadioButtonId();
        String jenisPesan = "";
        switch (id) {
            case R.id.musibah:
                jenisPesan = "musibah";
                break;
            case R.id.kriminal:
                jenisPesan = "kriminal";
                break;
            case R.id.lainnya:
                jenisPesan = "lainnya";
                break;
        }

        progressDialog.show();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<KeadaanDarurat> call = apiInterface.simpanKeadaanDarurat(pesandarurat, jenisPesan, getId);

        call.enqueue(new Callback<KeadaanDarurat>() {
            @Override
            public void onResponse(@NonNull Call<KeadaanDarurat> call, @NonNull Response<KeadaanDarurat> response) {
                progressDialog.dismiss();

                if (response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        Toast.makeText(KeadaanDaruratActivity.this,
                                response.body().getMessage(),
                                Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(KeadaanDaruratActivity.this,
                                response.body().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<KeadaanDarurat> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(KeadaanDaruratActivity.this,
                        t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
