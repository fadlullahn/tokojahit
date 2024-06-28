package com.example.tokojahit.Model.Login;

import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("user_id")
    private String userId;

    @SerializedName("name")
    private String name;

    @SerializedName("username")
    private String username;

    @SerializedName("level")
    private String level;
    @SerializedName("password")
    private String password;

    @SerializedName("email")
    private String email;
    @SerializedName("nowa")
    private String nowa;

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setLevel(String level){
        this.level = level;
    }

    public String getLevel(){
        return level;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setNowa(String nowa){
        this.nowa = nowa;
    }

    public String getNowa(){
        return nowa;
    }
}
