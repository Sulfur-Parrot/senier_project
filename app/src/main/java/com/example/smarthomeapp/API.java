package com.example.smarthomeapp;

import com.example.smarthomeapp.model.BaseResponse;
import com.example.smarthomeapp.model.MemberListData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    @POST("App/")
    @FormUrlEncoded
    Call<MemberListData> getMemberList(@FieldMap(encoded = true)HashMap<String, String> body);
    HashMap<String, String> param = new HashMap<>();
    param.put("userId", "사용자 아이디");
    param.put("password", "입력한 비번");

    RetrofitClient.getRetrofitInterface().login(param);

}