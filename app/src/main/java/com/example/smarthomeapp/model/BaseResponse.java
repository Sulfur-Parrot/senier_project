package com.example.smarthomeapp.model;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("code")
    public int code;

    @SerializedName("msg")
    public String message;
}