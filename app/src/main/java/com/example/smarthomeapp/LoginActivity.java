package com.example.smarthomeapp;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;


public class LoginActivity extends AppCompatActivity {
    private EditText et_id, et_pass;
    private Button btn_login, btn_register;
    private String auto_id, auto_pass;

    public static LoginActivity loginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginActivity = LoginActivity.this;

        et_id = findViewById(R.id.login_id);
        et_pass = findViewById(R.id.login_password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.login_btn_register);

        SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
        SharedPreferences.Editor autoLoginEdit = sharedPreferences.edit();

        auto_id = sharedPreferences.getString("email", null);
        auto_pass = sharedPreferences.getString("password", null);

        if(auto_id != null && auto_pass != null) {
            Toast.makeText(getApplicationContext(), "자동 로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


        //회원가입 버튼 클릭 시 수행
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //로그인 버튼 클릭 시 수행
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText에 현재 입력되어있는 값을 가져온다.
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println(response);
                            JSONObject jsonObject = new JSONObject(response);
                            System.out.println(jsonObject);

                            String hashedPass = jsonObject.getString("password");
                            boolean success = jsonObject.getBoolean("success");
                            boolean isValidPassword = BCrypt.checkpw(userPass, hashedPass);
                            System.out.println(hashedPass+ ", " + success);

                            if(success) { //일치하는 아이디가 있는 경우

                                if (isValidPassword) { //패스워드 검증 성공한 경우
                                    //자동 로그인데이터 저장
                                    autoLoginEdit.putString("email", userID);
                                    autoLoginEdit.putString("password", hashedPass);
                                    autoLoginEdit.apply();

                                    Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else { //패스워드 검증 실패한 경우
                                    Toast.makeText(getApplicationContext(), "패스워드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                            } else { //일치하는 아이디가 없는 경우
                                Toast.makeText(getApplicationContext(), "일치하는 아이디가 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

    }
}
