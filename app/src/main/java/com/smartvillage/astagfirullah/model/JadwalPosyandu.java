package com.smartvillage.astagfirullah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JadwalPosyandu {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("lokasi")
    @Expose
    private String lokasi;
    @SerializedName("dusun")
    @Expose
    private String dusun;
    @SerializedName("id_posyandu")
    @Expose
    private int idPosyandu;
    @SerializedName("tgl_yandu")
    @Expose
    private String tglYandu;
    @SerializedName("waktuyandu")
    @Expose
    private String waktuyandu;

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

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getDusun() {
        return dusun;
    }

    public void setDusun(String dusun) {
        this.dusun = dusun;
    }

    public int getIdPosyandu() {
        return idPosyandu;
    }

    public void setIdPosyandu(int idPosyandu) {
        this.idPosyandu = idPosyandu;
    }

    public String getTglYandu() {
        return tglYandu;
    }

    public void setTglYandu(String tglYandu) {
        this.tglYandu = tglYandu;
    }

    public String getWaktuyandu() {
        return waktuyandu;
    }

    public void setWaktuyandu(String waktuyandu) {
        this.waktuyandu = waktuyandu;
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
