package com.example.smarthomeapp;

import android.util.Log;

import java.io.IOException;

public class NetworkNotConnectedException extends IOException {
    public static int d(String tag, String msg) {
        Log.d("notConnected", "연결 안됨");
        return 1;
    }
}