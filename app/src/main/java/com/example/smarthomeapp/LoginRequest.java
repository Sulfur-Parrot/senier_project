package com.example.smarthomeapp;

import com.google.gson.annotations.SerializedName;
//DTO 받아오기
public class LoginRequest {
    @SerializedName("email")
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

    @Override
    public String toString() {
        return "LoginRequest{" +
                "id=" + email +
                ", password=" + passwd +
                '}';
    }
}