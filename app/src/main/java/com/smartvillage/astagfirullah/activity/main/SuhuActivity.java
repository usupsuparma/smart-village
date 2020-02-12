package com.smartvillage.astagfirullah.activity.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;

public class SuhuActivity extends AppCompatActivity {

    private TextView suhu, kelembaban, kualitas, ketkualitas;

    SessionManager sessionManager;
    String getId;
    String id;
    private static final String TAG = SuhuActivity.class.getSimpleName();
    private static String URL_READ = "https://simdesapp.windstandrobotic.org/api.php?id=get_suhu";
    private static String READ_KELEMBABAN = "https://simdesapp.windstandrobotic.org/api.php?id=get_nilaikelembapan";
    private static String READ_KUALITAS = "https://simdesapp.windstandrobotic.org/api.php?id=get_nilaikualitas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suhu);

        getSupportActionBar().setTitle("Suhu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        suhu = findViewById(R.id.suhu);
        kelembaban = findViewById(R.id.kelembaban);
        kualitas = findViewById(R.id.Kualitas);
        ketkualitas = findViewById(R.id.Ketkualitas);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);
        id="1";
    }

    private void getUserDetail(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (success.equals("1")){

                                for (int i =0; i < jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String strSuhu = object.getString("ph").trim();

                                    suhu.setText(strSuhu);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(SuhuActivity.this, "Error Reading Detail "+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(SuhuActivity.this, "Error Reading Detail "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
            {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void getKelembaban(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, READ_KELEMBABAN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.i(TAG, response.toString());

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("read");

                    if (success.equals("1")){

                        for (int i =0; i < jsonArray.length(); i++){

                            JSONObject object = jsonArray.getJSONObject(i);
                            String strKelembaban = object.getString("n_kl").trim();

                            kelembaban.setText(strKelembaban);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                    Toast.makeText(SuhuActivity.this, "Error Reading Detail "+e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(SuhuActivity.this, "Error Reading Detail "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void getKualitas(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, READ_KUALITAS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.i(TAG, response.toString());

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("read");

                    if (success.equals("1")){

                        for (int i =0; i < jsonArray.length(); i++){

                            JSONObject object = jsonArray.getJSONObject(i);
                            String strKualitas = object.getString("Nilai").trim();
                            String strKetkualitas = object.getString("Keterangan").trim();

                            kualitas.setText(strKualitas);
                            ketkualitas.setText(strKetkualitas);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                    Toast.makeText(SuhuActivity.this, "Error Reading Detail "+e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(SuhuActivity.this, "Error Reading Detail "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    protected void onResume() {
        super.onResume();
        getUserDetail();
        getKelembaban();
        getKualitas();
    }
}
