package com.example.tokojahit.Model.Desain;

import com.google.gson.annotations.SerializedName;

public class PostPutDelDesain {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Desain mDesain;
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
    public Desain getDesain() {
        return mDesain;
    }
    public void setDesain(Desain Desain) {
        mDesain = Desain;
    }
}
