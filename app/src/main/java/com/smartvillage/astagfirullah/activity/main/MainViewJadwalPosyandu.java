package com.smartvillage.astagfirullah.activity.main;

import com.smartvillage.astagfirullah.model.JadwalPosyandu;

import java.util.List;

public interface MainViewJadwalPosyandu {

    void showLoading();
    void hideLoading();
    void onGetResult(List<JadwalPosyandu> jadwalPosyanduList);
    void onErrorLoading(String message);
}
