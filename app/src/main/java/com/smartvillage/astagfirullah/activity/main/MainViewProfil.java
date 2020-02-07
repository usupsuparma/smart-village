package com.smartvillage.astagfirullah.activity.main;

import com.smartvillage.astagfirullah.model.Profil;

import java.util.List;

public interface MainViewProfil {

    void showLoading();
    void hideLoading();
    void onGetResult(List<Profil> profilList);
    void onErrorLoading(String message);

}
