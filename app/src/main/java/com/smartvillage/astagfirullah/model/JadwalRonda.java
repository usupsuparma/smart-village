package com.smartvillage.astagfirullah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JadwalRonda {

    @Expose
    @SerializedName("id") private int id;

    @Expose
    @SerializedName("namapetugas") private String namapetugas;

    @Expose
    @SerializedName("jadwalpetugas") private String jadwalpetugas;

    @Expose
    @SerializedName("id_hari") private int idHari;

    @Expose
    @SerializedName("nik") private String nik;

    @Expose
    @SerializedName("success") private Boolean success;

    @Expose
    @SerializedName("message") private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamapetugas() {
        return namapetugas;
    }

    public void setNamapetugas(String namapetugas) {
        this.namapetugas = namapetugas;
    }

    public String getJadwalpetugas() {
        return jadwalpetugas;
    }

    public void setJadwalpetugas(String jadwalpetugas) {
        this.jadwalpetugas = jadwalpetugas;
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

    public int getIdHari() {
        return idHari;
    }

    public void setIdHari(int idHari) {
        this.idHari = idHari;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }
}
