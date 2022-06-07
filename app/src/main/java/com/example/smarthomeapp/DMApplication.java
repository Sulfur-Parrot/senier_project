package com.example.smarthomeapp;

import android.app.Application;
import android.content.Context;

public class DMApplication extends Application {
    private static DMApplication appContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        appContext = this;
    }

    public static Context getContext() {
        return appContext;
    }
}
