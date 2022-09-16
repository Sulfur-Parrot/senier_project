package com.example.smarthomeapp;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class AlertListRequest extends StringRequest {

    //서버 url 설정(php파일 연동)
    final static private String URL = "http://172.30.7.98/php_appalert.php";
    private Map<String, String> map;


    public AlertListRequest(Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
