package com.example.tokojahit.Model.Desain;

import com.google.gson.annotations.SerializedName;

public class Desain {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private String price;
    @SerializedName("date")
    private String date;
    @SerializedName("image")
    private String image;

    public Desain(){}

    public Desain(String id, String name, String price, String date, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
