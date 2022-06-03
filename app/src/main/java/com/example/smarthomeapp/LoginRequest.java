package com.example.smarthomeapp;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("id")
    public String email;

    @SerializedName("password")
    public String passwd;

    public String getEmail(){
        return email;
    }
    public String getPasswd(){
        return passwd;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public void setPasswd(String passwd){
        this.passwd = passwd;
    }

    public LoginRequest(String email, String passwd){
        this.email = email;
        this.passwd = passwd;
    }
}