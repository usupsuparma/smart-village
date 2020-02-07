package com.smartvillage.astagfirullah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JadwalPosyandu {

    @Expose
    @SerializedName("id") private int id;

    @Expose
    @SerializedName("namabidan") private String namabidan;

    @Expose
    @SerializedName("waktuyandu") private String waktuyandu;

    @Expose
    @SerializedName("jadwalbidan") private String jadwalbidan;

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

    public String getNamabidan() {
        return namabidan;
    }

    public void setNamabidan(String namabidan) {
        this.namabidan = namabidan;
    }

    public String getWaktuyandu() {
        return waktuyandu;
    }

    public void setWaktuyandu(String waktuyandu) {
        this.waktuyandu = waktuyandu;
    }

    public String getJadwalbidan() {
        return jadwalbidan;
    }

    public void setJadwalbidan(String jadwalbidan) {
        this.jadwalbidan = jadwalbidan;
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
