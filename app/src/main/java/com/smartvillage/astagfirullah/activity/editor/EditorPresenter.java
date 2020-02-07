package com.smartvillage.astagfirullah.activity.editor;

import androidx.annotation.NonNull;

import com.smartvillage.astagfirullah.api.ApiClient;
import com.smartvillage.astagfirullah.api.ApiInterface;
import com.smartvillage.astagfirullah.model.JadwalPosyandu;
import com.smartvillage.astagfirullah.model.JadwalRonda;
import com.smartvillage.astagfirullah.model.RiwayatSakit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenter {

    private EditorView view;

    public EditorPresenter(EditorView view) {
        this.view = view;
    }

//  RIWAYAT SAKIT SIMPAN    ========================================================================
    void simpanRiwayatSakit(final String namapasien, final String penyakitpasien) {

        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<RiwayatSakit> call = apiInterface.simpanRiwayatSakit(namapasien, penyakitpasien);
        call.enqueue(new Callback<RiwayatSakit>() {
            @Override
            public void onResponse(@NonNull Call<RiwayatSakit> call, @NonNull Response<RiwayatSakit> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null ) {
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RiwayatSakit> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

//  RIWAYAT SAKIT UPDATE    ========================================================================
    public void updateRiwayatSakit(int id, String namapasien, String penyakitpasien) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<RiwayatSakit> call = apiInterface.updateRiwayatSakit(id, namapasien, penyakitpasien);
        call.enqueue(new Callback<RiwayatSakit>() {
            @Override
            public void onResponse(@NonNull Call<RiwayatSakit> call, @NonNull Response<RiwayatSakit> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RiwayatSakit> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

//  RIWAYAT SAKIT DELETE    ========================================================================
    public void deleteRiwayatSakit(int id) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<RiwayatSakit> call = apiInterface.deleteRiwayatSakit(id);

        call.enqueue(new Callback<RiwayatSakit>() {
            @Override
            public void onResponse(@NonNull Call<RiwayatSakit> call, @NonNull Response<RiwayatSakit> response){
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RiwayatSakit> call, @NonNull Throwable t){
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

//  JADWAL RONDA SIMPAN     ========================================================================
    void simpanJadwalRonda(final String namaPetugas, final String jadwalPetugas) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<JadwalRonda> call = apiInterface.simpanJadwalRonda(namaPetugas, jadwalPetugas);

        call.enqueue(new Callback<JadwalRonda>() {
            @Override
            public void onResponse(@NonNull Call<JadwalRonda> call, @NonNull Response<JadwalRonda> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<JadwalRonda> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    //  JADWAL RONDA UPDATE     ========================================================================
    public void updateJadwalRonda(int id, String namapetugas, String jadwalpetugas) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<JadwalRonda> call = apiInterface.updateJadwalRonda(id, namapetugas, jadwalpetugas);
        call.enqueue(new Callback<JadwalRonda>() {
            @Override
            public void onResponse(@NonNull Call<JadwalRonda> call, @NonNull Response<JadwalRonda> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<JadwalRonda> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

//  JADWAL RONDA DELETE     ========================================================================
    public void deleteJadwalRonda(int id) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<JadwalRonda> call = apiInterface.deleteJadwalRonda(id);

        call.enqueue(new Callback<JadwalRonda>() {
            @Override
            public void onResponse(@NonNull Call<JadwalRonda> call, @NonNull Response<JadwalRonda> response){
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<JadwalRonda> call, @NonNull Throwable t){
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

//  JADWAL POSYANDU SIMPAN  ========================================================================
    void simpanJadwalPosyandu( String namabidan,  String jadwalbidan,   String waktuyandu) {

        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<JadwalPosyandu> call = apiInterface.simpanJadwalPosyandu(namabidan, jadwalbidan, waktuyandu);
        call.enqueue(new Callback<JadwalPosyandu>() {
            @Override
            public void onResponse(@NonNull Call<JadwalPosyandu> call, @NonNull Response<JadwalPosyandu> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null ) {
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<JadwalPosyandu> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

//  JADWAL POSYANDU UPDATE     ========================================================================
    public void updateJadwalPosyandu(int id,  String namabidan,  String jadwalbidan,   String waktuyandu) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<JadwalPosyandu> call = apiInterface.updateJadwalPosyandu(id, namabidan, jadwalbidan, waktuyandu);
        call.enqueue(new Callback<JadwalPosyandu>() {
            @Override
            public void onResponse(@NonNull Call<JadwalPosyandu> call, @NonNull Response<JadwalPosyandu> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<JadwalPosyandu> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    //  JADWAL RONDA DELETE     ========================================================================
    public void deleteJadwalPosyandu(int id) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<JadwalPosyandu> call = apiInterface.deleteJadwalPosyandu(id);

        call.enqueue(new Callback<JadwalPosyandu>() {
            @Override
            public void onResponse(@NonNull Call<JadwalPosyandu> call, @NonNull Response<JadwalPosyandu> response){
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<JadwalPosyandu> call, @NonNull Throwable t){
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

}
