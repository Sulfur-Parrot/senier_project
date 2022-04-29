package com.example.smarthomeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_loading);    //theme을 활용했다면 지운다

        moveMain(1);    //1초후 MainActivity로 넘어감
    }

    private void moveMain(int sec) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //new Intent(현재 context, 이동할 activity)
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);  //intent에 명시한 액티비티로 이동

                finish();   //현재 액티비티 종료
            }
        }, 1000*sec);
    }
}