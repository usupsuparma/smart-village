package com.smartvillage.astagfirullah.activity.main;

import android.util.Log;

import com.smartvillage.astagfirullah.api.ApiClient;
import com.smartvillage.astagfirullah.api.ApiInterface;
import com.smartvillage.astagfirullah.model.RiwayatSakit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterRiwayatSakit {

    private MainViewRiwayatSakit view;

    public MainPresenterRiwayatSakit(MainViewRiwayatSakit view) {
        this.view = view;
    }

    void getData(String id){
        view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        apiInterface.getRiwayatSakit(id).enqueue(new Callback<List<RiwayatSakit>>() {
            @Override
            public void onResponse(Call<List<RiwayatSakit>> call, Response<List<RiwayatSakit>> response) {
                view.hideLoading();
                if (response.isSuccessful()){
                    view.onGetResult(response.body());
                } else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<RiwayatSakit>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.toString());
            }
        });
    }

}
