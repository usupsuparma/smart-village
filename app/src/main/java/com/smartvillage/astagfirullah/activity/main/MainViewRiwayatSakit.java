package com.smartvillage.astagfirullah.activity.main;

import com.smartvillage.astagfirullah.model.RiwayatSakit;

import java.util.List;

public interface MainViewRiwayatSakit {

    void showLoading();
    void hideLoading();
    void onGetResult(List<RiwayatSakit> riwayatSakitList);
    void onErrorLoading(String message);

}
