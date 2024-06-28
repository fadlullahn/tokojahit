package com.example.tokojahit.Model.Pesanan;

import com.google.gson.annotations.SerializedName;

public class PostPutDelPesanan {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Pesanan mPesanan;
    @SerializedName("message")
    String message;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Pesanan getPesanan() {
        return mPesanan;
    }
    public void setPesanan(Pesanan Pesanan) {
        mPesanan = Pesanan;
    }
}
