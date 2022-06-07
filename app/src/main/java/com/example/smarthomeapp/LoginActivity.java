package com.example.smarthomeapp;

import static com.example.smarthomeapp.API.param;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.*;


public class LoginActivity extends AppCompatActivity {
    private EditText et_id, et_pass;
    private Button btn_login, btn_register;
    private RetrofitClient retrofitClient;
    private API API;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.login_id);
        et_pass = findViewById(R.id.login_password);
        btn_login = findViewById(R.id.login_loginbtn);
        btn_register = findViewById(R.id.login_register);

        //회원가입 버튼 클릭 시 수행
        /*btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        }); */

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //RetrofitClient.getRetrofitInterface().login(param).enqueue(new RetroCallback<Login>)
            }
        });


    }
}
