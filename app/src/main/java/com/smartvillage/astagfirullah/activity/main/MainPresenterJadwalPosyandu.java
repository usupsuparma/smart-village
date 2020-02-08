package com.smartvillage.astagfirullah.activity.main;

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
        apiInterface.getJadwalPosyandu().enqueue(new Callback<List<JadwalPosyandu>>() {
            @Override
            public void onResponse(Call<List<JadwalPosyandu>> call, Response<List<JadwalPosyandu>> response) {
                view.hideLoading();
                if (response.isSuccessful()) {
                    view.onGetResult(response.body());
                } else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<JadwalPosyandu>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }

}
