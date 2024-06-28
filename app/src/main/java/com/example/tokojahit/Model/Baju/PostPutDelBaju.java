package com.example.tokojahit.Model.Baju;

import com.google.gson.annotations.SerializedName;

public class PostPutDelBaju {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Baju mBaju;
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
    public Baju getBaju() {
        return mBaju;
    }
    public void setBaju(Baju Baju) {
        mBaju = Baju;
    }
}
