package com.androiddiplomski.data.api.models.request;


import com.google.gson.annotations.SerializedName;

public class LoginRequest {


    @SerializedName("username")
    public String username;


    @SerializedName("password")
    public String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
