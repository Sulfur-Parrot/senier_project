package com.example.smarthomeapp.model;

import com.google.gson.annotations.SerializedName;

public class Member {
    //@SerializedName("id") public String id;
    @SerializedName("email") public String email;
    @SerializedName("password") public String passwd;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

}