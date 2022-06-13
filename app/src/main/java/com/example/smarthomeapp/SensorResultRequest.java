package com.example.smarthomeapp;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//백그라운드에서 센서에서 비정상적인 값 감지되었을 때 알림 울리기
public class SensorResultRequest extends StringRequest {

    //서버 url 설정(php파일 연동)
    final static private String URL = "http://220.149.148.40/php_appsensor.php";
    private Map<String, String> map;


    public SensorResultRequest(Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
