package com.smartvillage.astagfirullah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profil {

    @Expose
    @SerializedName("nik") private int nik;

    @Expose
    @SerializedName("nama") private String nama;

    @Expose
    @SerializedName("tanggallahir") private String tanggallahir;

    @Expose
    @SerializedName("jeniskelamin") private String jeniskelamin;

    @Expose
    @SerializedName("alamat") private String alamat;

    @Expose
    @SerializedName("agama") private String agama;

    @Expose
    @SerializedName("status") private String status;

    @Expose
    @SerializedName("pekerjaan") private String pekerjaan;

    @Expose
    @SerializedName("success") private Boolean success;

    @Expose
    @SerializedName("message") private String message;


    public int getNik() {
        return nik;
    }

    public void setNik(int nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggallahir() {
        return tanggallahir;
    }

    public void setTanggallahir(String tanggallahir) {
        this.tanggallahir = tanggallahir;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
