package com.smartvillage.astagfirullah.activity.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.session.MediaSessionManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smartvillage.astagfirullah.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private EditText nik, password;
    private Button BtnMasuk;
    private ProgressBar loading;
    private static String URL_LOGIN = "https://simdesapp.windstandrobotic.org/leder_login.php";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);
        loading = findViewById(R.id.loading);

        nik = findViewById(R.id.nik);
        password = findViewById(R.id.password);
        BtnMasuk = findViewById(R.id.BtnMasuk);

        BtnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mnik = nik.getText().toString().trim();
                String mpassword = password.getText().toString().trim();

                if (!mnik.isEmpty() || !mpassword.isEmpty()) {
                    login(mnik, mpassword);
                } else {
                    nik.setError("Masukkan NIK");
                    password.setError("Masukkan Password");
                }
            }
        });
    }

    private void login(final String nik, String password) {
        loading.setVisibility(View.VISIBLE);
        BtnMasuk.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                        if (success.equals("1")) {
                            for (int i = 0; i < jsonArray.length(); i++ ) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String nama = object.getString("level_user").trim();
                                String nik = object.getString("nik").trim();
                                String id = object.getString("id_user").trim();

                                sessionManager.createSession(nama, nik, id);
                                    Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                                    intent.putExtra("level_user", nama);
                                    intent.putExtra("nik", nik);
                                    startActivity(intent);
                                finish();

                                loading.setVisibility(View.GONE);
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "NIK atau Password Salah!", Toast.LENGTH_SHORT).show();
                        }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.setVisibility(View.GONE);
                            BtnMasuk.setVisibility(View.VISIBLE);
                            Toast.makeText(MainActivity.this,"Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        BtnMasuk.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this,"Error" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nik", nik);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}