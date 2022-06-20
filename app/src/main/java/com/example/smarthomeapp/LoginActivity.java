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

import com.example.smarthomeapp.model.BaseResponse;
import com.example.smarthomeapp.model.Member;
import com.example.smarthomeapp.model.MemberListData;

import java.util.HashMap;

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
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();

                if (userID.trim().length() == 0 || userPass.trim().length() == 0 || userID == null || userPass == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("알림")
                            .setMessage("로그인 정보를 입력바랍니다.")
                            .setPositiveButton("확인", null)
                            .create()
                            .show();
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    //로그인통신
                    LoginResponse();
                    Log.d("login", "로그인 통신 시작");
                }

            }
        });


    }
    public void LoginResponse() {
        String userID = et_id.getText().toString().trim();
        String userPasswd = et_pass.getText().toString().trim();

        param.put("userId", userID);
        param.put("password", userPasswd);
        Log.d("retrofit", "해쉬맵에 put");
        RetroClient.getApiInterface().login(param).enqueue(new RetroCallback<MemberListData>() {
            @Override
            public void sendRequestSuccess(BaseResponse receiveData) {
                Log.d("retrofit", "송신 성공 시작");

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
                LoginActivity.this.finish();
                Toast.makeText(LoginActivity.this, userID + "님 환영합니다.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void sendRequestFail(BaseResponse response) {
                Log.d("retrofit", "송신 실패");

                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("알림")
                        .setMessage("우리도 알 수 없는 오류가 발생\n")
                        .setPositiveButton("확인", null)
                        .create()
                        .show();
            }
        });
    }
}
