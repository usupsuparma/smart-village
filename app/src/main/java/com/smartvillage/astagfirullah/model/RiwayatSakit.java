package com.smartvillage.astagfirullah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RiwayatSakit {

    @Expose
    @SerializedName("id") private int id;

    @Expose
    @SerializedName("namapasien") private String namapasien;

    @Expose
    @SerializedName("penyakitpasien") private String penyakitpasien;

    @Expose
    @SerializedName("tanggalsakit") private String tanggalsakit;

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

    public String getNamapasien() {
        return namapasien;
    }

    public void setNamapasien(String namapasien) {
        this.namapasien = namapasien;
    }

    public String getPenyakitpasien() {
        return penyakitpasien;
    }

    public void setPenyakitpasien(String penyakitpasien) {
        this.penyakitpasien = penyakitpasien;
    }

    public String getTanggalsakit() {
        return tanggalsakit;
    }

    public void setTanggalsakit(String tanggalsakit) {
        this.tanggalsakit = tanggalsakit;
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