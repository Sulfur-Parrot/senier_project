package com.example.smarthomeapp;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class SensorMeasureRequest extends StringRequest {

    //서버 url 설정(php파일 연동)
    final static private String URL = "http://172.30.7.125/php_appconnection.php";
    private Map<String, String> map;


    public SensorMeasureRequest(Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
