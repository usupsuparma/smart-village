package com.smartvillage.astagfirullah.activity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import com.smartvillage.astagfirullah.R;
import com.smartvillage.astagfirullah.model.KeadaanDarurat;
import com.smartvillage.astagfirullah.model.RiwayatSakit;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    CardView suhu, riwayatsakit, websitedesa, jadwalronda, jadwalposyandu, keadaandarurat, profil, keluar;
    String getId;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new DashboardActivity.FirebaseNotificationOpenedHandler(this))
                .init();

        keluar = findViewById(R.id.keluar);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
            }
        });

        suhu = findViewById(R.id.suhu);
        suhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, SuhuActivity.class);
                startActivity(i);
            }
        });

        riwayatsakit = findViewById(R.id.riwayatsakit);
        riwayatsakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, RiwayatSakitActivity.class);
                startActivity(i);
            }
        });

        jadwalronda = findViewById(R.id.jadwalronda);
        jadwalronda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, JadwalRondaActivity.class);
                startActivity(i);
            }
        });

        jadwalposyandu = findViewById(R.id.jadwalposyandu);
        jadwalposyandu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, JadwalPosyanduActivity.class);
                startActivity(i);
            }
        });

        keadaandarurat = findViewById(R.id.keadaandarurat);
        keadaandarurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, KeadaanDaruratActivity.class);
                startActivity(i);
            }
        });

        profil = findViewById(R.id.profil);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, ProfilActivity.class);
                startActivity(i);
            }
        });
    }

    //DIRECT WEBSITE DESA!
    public void websitedesa(View view) {
        Intent websitedesaIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.simdesapp.windstandrobotic.org"));
        startActivity(websitedesaIntent);
    }

    public class FirebaseNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        Context ctx;

        FirebaseNotificationOpenedHandler(Context context) {
            ctx = context;
        }

        // This fires when a notification is opened by tapping on it.
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
            OSNotificationAction.ActionType actionType = result.action.type;
            JSONObject data = result.notification.payload.additionalData;
            Toast.makeText(ctx, "Halo, saya klik notifikasi ya", Toast.LENGTH_SHORT).show();

            Object activityToLaunch = MainActivity.class;

            String buka_activity;
            String activity;
            if (data != null) {

                buka_activity = data.optString("pilih_activity", null);

                /* Action By Tap Notification */
                if (buka_activity != null && buka_activity.equals("PenerimaActivity"))
                {
                    Log.i("OneSignal", "customkey set with value: " + buka_activity);

                    activityToLaunch = DashboardActivity.class;

                    Toast.makeText(ctx, "Membuka Halaman", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), (Class<?>) activityToLaunch);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(intent);
                }
//
//                else if (buka_activity != null && buka_activity.equals("listlahan"))
//                {
//                    Log.i("OneSignal", "customkey set with value: " + buka_activity);
//
//                    activityToLaunch = listlahan.class;
//
//                    Toast.makeText(ctx, "Deteksi Nutrisi Gagal, Ulangi Proses Deteksi", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), (Class<?>) activityToLaunch);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    ctx.startActivity(intent);
//                }
//
//                else if (buka_activity != null && buka_activity.equals("status"))
//                {
//                    Log.i("OneSignal", "customkey set with value: " + buka_activity);
//
//                    activityToLaunch = status.class;
//
//                    Toast.makeText(ctx, "Deteksi Nutrisi Berhasil", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), (Class<?>) activityToLaunch);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    ctx.startActivity(intent);
//                }
//
//                else if (buka_activity != null && buka_activity.equals("pengecekan"))
//                {
//                    Log.i("OneSignal", "customkey set with value: " + buka_activity);
//
//                    activityToLaunch = pengecekan.class;
//
//                    Toast.makeText(ctx, "Membuka Halaman", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), (Class<?>) activityToLaunch);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    ctx.startActivity(intent);
//                }

                else{
                    Log.i("OneSignal", "customkey set with value: " + buka_activity);

                    activityToLaunch = DashboardActivity.class;

                    Toast.makeText(ctx, "Membuka Halaman", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), (Class<?>) activityToLaunch);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(intent);
                }
            }

            if (actionType == OSNotificationAction.ActionType.ActionTaken) {
                Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);

                /* Action By Klik Button */
                if (result.action.actionID.equals("btn_1")) {
                    Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);
                    activityToLaunch = DashboardActivity.class;
                    Intent intent = new Intent(getApplicationContext(), (Class<?>) activityToLaunch);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(intent);
                }
                else if (result.action.actionID.equals("btn_2")) {
                    Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);
                    activityToLaunch = DashboardActivity.class;
                    Intent intent = new Intent(getApplicationContext(), (Class<?>) activityToLaunch);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(intent);
                }
                else {
                    Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);
                    activityToLaunch = DashboardActivity.class;
                    Intent intent = new Intent(getApplicationContext(), (Class<?>) activityToLaunch);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(intent);
                }
            }
        }
    }

}