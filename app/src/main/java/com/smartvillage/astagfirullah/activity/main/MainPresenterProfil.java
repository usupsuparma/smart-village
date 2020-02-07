package com.smartvillage.astagfirullah.activity.main;

import android.util.Log;

import androidx.annotation.NonNull;

import com.smartvillage.astagfirullah.api.ApiClient;
import com.smartvillage.astagfirullah.api.ApiInterface;
import com.smartvillage.astagfirullah.model.Profil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterProfil {

    private MainViewProfil view;

    public MainPresenterProfil(MainViewProfil view) {
        this.view = view;
    }

    void getData(){

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Profil>> call = apiInterface.getProfil();
        call.enqueue(new Callback<List<Profil>>() {

            @Override
            public void onResponse(@NonNull Call<List<Profil>> call, @NonNull Response<List<Profil>> response) {
                Log.d("TAG", "onResponse: "+response);
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Profil>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
