package com.example.tokojahit.Model.Kain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetKain {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Kain> listDataKain;
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
    public List<Kain> getListDataKain() {
        return listDataKain;
    }
    public void setListDataKain(List<Kain> listDataKain) {
        this.listDataKain = listDataKain;
    }
}
