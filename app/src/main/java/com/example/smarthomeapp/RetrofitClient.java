package com.example.smarthomeapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private static API API;
    //사용하고 있는 서버 BASE 주소
    private static String baseURL = "http://220.149.148.40:8080/";

    private RetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API = retrofit.create(API.class);
    }

    public static RetrofitClient getInstance(){
        if (instance == null){
            instance = new RetrofitClient();
        }
        return instance;
    }

    public static API getRetrofitInterface() {
        return API;
    }


    //필요 없는 자파 파일
}
