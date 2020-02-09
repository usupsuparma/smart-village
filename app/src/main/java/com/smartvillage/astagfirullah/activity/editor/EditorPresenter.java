package com.smartvillage.astagfirullah.activity.editor;

import android.util.Log;

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

    //  JADWAL RONDA SIMPAN     ========================================================================
    void simpanJadwalRonda(final String namaPetugas, final int idJadwalHari) {
        Log.d("testme", "simpanJadwalRonda: "+idJadwalHari);
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<JadwalRonda> call = apiInterface.simpanJadwalRonda(namaPetugas, idJadwalHari);

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
    public void updateJadwalRonda(int id, String namapetugas, int idHari) {
        Log.d("testme", "updateJadwalRonda: "+idHari);
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<JadwalRonda> call = apiInterface.updateJadwalRonda(id, namapetugas, idHari);
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

    //  JADWAL POSYANDU SIMPAN  ========================================================================
    void simpanJadwalPosyandu(String tanggalPosyandu, String waktuyandu, int idTempatPosyandu) {

        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<JadwalPosyandu> call = apiInterface.simpanJadwalPosyandu(tanggalPosyandu, waktuyandu, idTempatPosyandu);
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

    //  JADWAL POSYANDU UPDATE     ========================================================================
    public void updateJadwalPosyandu(int id, String tanggalPosyandu, String waktuyandu, int idJadwalPosyandu) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<JadwalPosyandu> call = apiInterface.updateJadwalPosyandu(id, tanggalPosyandu, waktuyandu, idJadwalPosyandu);
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
        apiInterface.deleteJadwalPosyandu(id).enqueue(new Callback<JadwalPosyandu>() {
            @Override
            public void onResponse(Call<JadwalPosyandu> call, Response<JadwalPosyandu> response) {
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
            public void onFailure(Call<JadwalPosyandu> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

}
