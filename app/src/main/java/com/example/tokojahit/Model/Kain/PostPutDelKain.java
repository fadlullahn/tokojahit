package com.example.tokojahit.Model.Kain;

import com.google.gson.annotations.SerializedName;

public class PostPutDelKain {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Kain mKain;
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
    public Kain getKain() {
        return mKain;
    }
    public void setKain(Kain Kain) {
        mKain = Kain;
    }
}
