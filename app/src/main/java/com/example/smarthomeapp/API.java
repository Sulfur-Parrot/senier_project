package com.example.smarthomeapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {
    //@통신 방식("통신 API 명")
    @POST("/api_init_session")
    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);
}