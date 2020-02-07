package com.smartvillage.astagfirullah.activity.main;

import androidx.annotation.NonNull;

import com.smartvillage.astagfirullah.api.ApiClient;
import com.smartvillage.astagfirullah.api.ApiInterface;
import com.smartvillage.astagfirullah.model.JadwalPosyandu;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterJadwalPosyandu {

    private MainViewJadwalPosyandu view;

    public MainPresenterJadwalPosyandu(MainViewJadwalPosyandu view) {
        this.view = view;
    }

    void getData(){

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<JadwalPosyandu>> call = apiInterface.getJadwalPosyandu();
        call.enqueue(new Callback<List<JadwalPosyandu>>() {

            @Override
            public void onResponse(@NonNull Call<List<JadwalPosyandu>> call, @NonNull Response<List<JadwalPosyandu>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<JadwalPosyandu>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }

}
