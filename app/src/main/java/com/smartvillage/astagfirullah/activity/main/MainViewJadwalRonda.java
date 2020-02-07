package com.smartvillage.astagfirullah.activity.main;

import com.smartvillage.astagfirullah.model.JadwalRonda;

import java.util.List;

public interface MainViewJadwalRonda {

    void showLoading();
    void hideLoading();
    void onGetResult(List<JadwalRonda> jadwalRondaList);
    void onErrorLoading(String message);

}