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
    @POST("api.php?id=get_save_riwayatsakit")
    Call<RiwayatSakit> simpanRiwayatSakit (
            @Field("namapasien") String namapasien,
            @Field("penyakitpasien") String penyakitpasien );

    @FormUrlEncoded
    @POST("api.php?id=get_data_riwayatsakit")
    Call<List<RiwayatSakit>> getRiwayatSakit(
            @Field("id") String id);

    @FormUrlEncoded
    @POST("api.php?id=get_update_riwayatsakit")
    Call<RiwayatSakit> updateRiwayatSakit(
            @Field("id") int id,
            @Field("namapasien") String namapasien,
            @Field("penyakitpasien") String penyakitpasien );
            //@Field("tanggalsakit") String tanggalsakit );

    @FormUrlEncoded
    @POST("api.php?id=get_delete_riwayatsakit")
    Call<RiwayatSakit> deleteRiwayatSakit(
            @Field("id") int id );

//  JADWAL RONDA    ================================================================================

    @FormUrlEncoded
    @POST("api.php?id=get_save_jadwalronda")
    Call<JadwalRonda> simpanJadwalRonda (
            @Field("namapetugas") String namapetugas,
            @Field("id_hari") int idHari );

    @GET("api.php?id=get_data_jadwalronda")
    Call<List<JadwalRonda>> getJadwalRonda();

    @FormUrlEncoded
    @POST("api.php?id=get_update_jadwalronda")
    Call<JadwalRonda> updateJadwalRonda(
            @Field("id") int id,
            @Field("namapetugas") String namapetugas,
            @Field("id_hari") int idHari);

    @FormUrlEncoded
    @POST("api.php?id=get_delete_jadwalronda")
    Call<JadwalRonda> deleteJadwalRonda(
            @Field("id") int id );

//  JADWAL POSYANDU ================================================================================

    @FormUrlEncoded
    @POST("api.php?id=get_save_jadwalposyandu")
    Call<JadwalPosyandu> simpanJadwalPosyandu(
            @Field("tanggalyandu") String tanggalYandu,
            @Field("waktuyandu") String waktuyandu,
            @Field("idtempatposyandu") int idTempatPosyandu
            );

    @GET("api.php?id=get_data_jadwalposyandu")
    Call<List<JadwalPosyandu>> getJadwalPosyandu();

    @FormUrlEncoded
    @POST("api.php?id=get_update_jadwalposyandu")
    Call<JadwalPosyandu> updateJadwalPosyandu(
            @Field("id") int id,
            @Field("tanggal_posyandu") String tanggalPosyandu,
            @Field("waktu_yandu") String waktuyandu,
            @Field("id_jadwal_posyandu") int idJadwalPosyandu
    );

    @FormUrlEncoded
    @POST("api.php?id=get_delete_jadwalposyandu")
    Call<JadwalPosyandu> deleteJadwalPosyandu(
            @Field("id") int id );

//  KEADAAN DARURAT ================================================================================

    @FormUrlEncoded
    @POST("api.php?id=get_save_keadaandarurat")
    Call<KeadaanDarurat> simpanKeadaanDarurat (
    @Field("pesandarurat") String pesandarurat, @Field("jenispesan") String jenisPesan, @Field("getId") String getId );

//  PROFIL =========================================================================================

    @GET("api.php?id=get_data_profil")
    Call<List<Profil>> getProfil();
}
