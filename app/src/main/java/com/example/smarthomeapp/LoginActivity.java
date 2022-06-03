package com.example.smarthomeapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
                //EditText에 현재 입력되어있는 값을 get 해온다.
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
                }
            }
        });
    }

        public void LoginResponse() {
            String userID = et_id.getText().toString().trim();
            String userPasswd = et_pass.getText().toString().trim();

            //loginrequest에 사용자가 입력한 id와 pw를 저장
            LoginRequest loginRequest = new LoginRequest(userID, userPasswd);

            //Retrofit 생성
            retrofitClient = RetrofitClient.getInstance();
            API = RetrofitClient.getRetrofitInterface();

            //logingRequest에 저장된 데이터와 함께 API에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음
            API.getLoginResponse(loginRequest).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    Log.d("retrofit", "Data fetch success");

                    //통신 성공
                    if(response.isSuccessful() && response.body() != null){
                        //response.body() 를 result 에 저장
                        LoginResponse result = response.body();
                        //받은 코드 저장
                        String resultCode = result.getResultCode();
                        //받은 토큰 저장
                        String token = result.getToken();

                        String success = "200"; // 로그인 성공
                        String errorID = "300"; // 아이디 불일치
                        String errorPW = "400"; // 비밀번호 불일치

                        if(resultCode.equals(success)){
                            String userID = et_id.getText().toString();
                            String userPasswd = et_pass.getText().toString();

                            //다른 통신을 하기 위해 token 저장
                            //setPreference(token, token);

                            Toast.makeText(LoginActivity.this, userID + "님 환영합니다.", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("userID", userID);
                            startActivity(intent);
                            LoginActivity.this.finish();
                        } else if(resultCode.equals(errorID)) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setTitle("알림")
                                    .setMessage("아이디가 존재하지 않습니다.\n 회원가입을 하시거나, 다시 확인해주세요.")
                                    .setPositiveButton("확인", null)
                                    .create()
                                    .show();
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        } else if (resultCode.equals(errorPW)) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setTitle("알림")
                                    .setMessage("비밀번호가 일치하지 않습니다.\n 다시한번 확인해주시기 바랍니다.")
                                    .setPositiveButton("확인", null)
                                    .create()
                                    .show();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setTitle("알림")
                                    .setMessage("우리도 알 수 없는 오류가 발생\n")
                                    .setPositiveButton("확인", null)
                                    .create()
                                    .show();
                        }
                    }
                }
                //통신 실패
                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t){
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
