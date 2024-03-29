package com.example.smarthomeapp;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class LoginRequest extends StringRequest {

    //서버 url 설정(php파일 연동)
    final static private String URL = "http://172.30.7.125/php_login.php"; //"http://220.149.148.40/php_login.php";
    private Map<String, String> map;


    public LoginRequest(String userID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("email", userID);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}