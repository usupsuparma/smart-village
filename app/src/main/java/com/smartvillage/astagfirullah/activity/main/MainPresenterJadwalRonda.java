package com.smartvillage.astagfirullah.activity.main;

import androidx.annotation.NonNull;

import com.smartvillage.astagfirullah.api.ApiClient;
import com.smartvillage.astagfirullah.api.ApiInterface;
import com.smartvillage.astagfirullah.model.JadwalRonda;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterJadwalRonda {

    private MainViewJadwalRonda view;

    public MainPresenterJadwalRonda(MainViewJadwalRonda view) {
        this.view = view;
    }

    void getData(){

        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<JadwalRonda>> call = apiInterface.getJadwalRonda();
        call.enqueue(new Callback<List<JadwalRonda>>() {

            @Override
            public void onResponse(@NonNull Call<List<JadwalRonda>> call, @NonNull Response<List<JadwalRonda>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<JadwalRonda>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
