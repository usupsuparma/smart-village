package com.smartvillage.astagfirullah.api;

import com.smartvillage.astagfirullah.model.JadwalPosyandu;
import com.smartvillage.astagfirullah.model.JadwalRonda;
import com.smartvillage.astagfirullah.model.KeadaanDarurat;
import com.smartvillage.astagfirullah.model.Profil;
import com.smartvillage.astagfirullah.model.RiwayatSakit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

//  RIWAYAT SAKIT   ================================================================================

    @FormUrlEncoded
    @POST("get_save_riwayatsakit.php")
    Call<RiwayatSakit> simpanRiwayatSakit (
            @Field("namapasien") String namapasien,
            @Field("penyakitpasien") String penyakitpasien );
            //@Field("tanggalsakit") String tanggalsakit );

    @FormUrlEncoded
    @POST("get_data_riwayatsakit.php")
    Call<List<RiwayatSakit>> getRiwayatSakit(
            @Field("id") String id);

    @FormUrlEncoded
    @POST("get_update_riwayatsakit.php")
    Call<RiwayatSakit> updateRiwayatSakit(
            @Field("id") int id,
            @Field("namapasien") String namapasien,
            @Field("penyakitpasien") String penyakitpasien );
            //@Field("tanggalsakit") String tanggalsakit );

    @FormUrlEncoded
    @POST("get_delete_riwayatsakit.php")
    Call<RiwayatSakit> deleteRiwayatSakit(
            @Field("id") int id );

//  JADWAL RONDA    ================================================================================

    @FormUrlEncoded
    @POST("get_save_jadwalronda.php")
    Call<JadwalRonda> simpanJadwalRonda (
            @Field("namapetugas") String namapetugas,
            @Field("jadwalpetugas") String jadwalpetugas );

    @GET("get_data_jadwalronda.php")
    Call<List<JadwalRonda>> getJadwalRonda();

    @FormUrlEncoded
    @POST("get_update_jadwalronda.php")
    Call<JadwalRonda> updateJadwalRonda(
            @Field("id") int id,
            @Field("namapetugas") String namapetugas,
            @Field("jadwalpetugas") String jadwalpetugas );

    @FormUrlEncoded
    @POST("get_delete_jadwalronda.php")
    Call<JadwalRonda> deleteJadwalRonda(
            @Field("id") int id );

//  JADWAL POSYANDU ================================================================================

    @FormUrlEncoded
    @POST("get_save_jadwalposyandu.php")
    Call<JadwalPosyandu> simpanJadwalPosyandu(
            @Field("tanggalyandu") String tanggalYandu,
            @Field("waktuyandu") String waktuyandu,
            @Field("idtempatposyandu") int idTempatPosyandu
            );

    @GET("get_data_jadwalposyandu.php")
    Call<List<JadwalPosyandu>> getJadwalPosyandu();

    @FormUrlEncoded
    @POST("get_update_jadwalposyandu.php")
    Call<JadwalPosyandu> updateJadwalPosyandu(
            @Field("id") int id,
            @Field("tanggal_posyandu") String tanggalPosyandu,
            @Field("waktu_yandu") String waktuyandu,
            @Field("id_jadwal_posyandu") int idJadwalPosyandu
    );

    @FormUrlEncoded
    @POST("get_delete_jadwalposyandu.php")
    Call<JadwalPosyandu> deleteJadwalPosyandu(
            @Field("id") int id );

//  KEADAAN DARURAT ================================================================================

    @FormUrlEncoded
    @POST("get_save_keadaandarurat.php")
    Call<KeadaanDarurat> simpanKeadaanDarurat (
    @Field("pesandarurat") String pesandarurat, @Field("jenispesan") String jenisPesan, @Field("getId") String getId );

//  PROFIL =========================================================================================

    @GET("get_data_profil.php")
    Call<List<Profil>> getProfil();
}
