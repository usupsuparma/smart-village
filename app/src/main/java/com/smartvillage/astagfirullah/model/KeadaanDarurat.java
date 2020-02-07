package com.smartvillage.astagfirullah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KeadaanDarurat {

    @Expose
    @SerializedName("id") private int id;

    @Expose
    @SerializedName("pesandarurat") private String pesandarurat;

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

    public String getPesandarurat() {
        return pesandarurat;
    }

    public void setPesandarurat(String pesandarurat) {
        this.pesandarurat = pesandarurat;
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
