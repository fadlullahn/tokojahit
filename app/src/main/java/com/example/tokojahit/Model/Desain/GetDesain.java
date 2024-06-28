package com.example.tokojahit.Model.Desain;

import com.example.tokojahit.Model.Kain.Kain;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDesain {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Desain> listDataDesain;
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
    public List<Desain> getListDataDesain() {
        return listDataDesain;
    }
    public void setListDataDesain(List<Desain> listDataDesain) {
        this.listDataDesain = listDataDesain;
    }
}
