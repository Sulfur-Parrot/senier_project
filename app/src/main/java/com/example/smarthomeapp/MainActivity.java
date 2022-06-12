package com.example.smarthomeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;

public class MainActivity extends AppCompatActivity {
    final String TAG = this.getClass().getSimpleName();

    private BackKeyHandler backKeyHandler = new BackKeyHandler(this);

    LoginActivity loginActivity = (LoginActivity)LoginActivity.loginActivity;

    LinearLayout home_ly;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginActivity.finish();

        init(); //객체 정의
        SettingListener(); //리스너 등록

        //처음 시작할 탭 설정
        bottomNavigationView.setSelectedItemId(R.id.tab_home);
    }

    //뒤로 키 2번 누르면 종료
    @Override
    public void onBackPressed() {
        backKeyHandler.onBackPressed();
    }

    private void init() {
        home_ly = findViewById(R.id.home_ly);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    private void SettingListener() {
        //선택 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new TabSelectedListener());
    }

    class TabSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.tab_home: {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.home_ly, new HomeFragment())
                            .commit();
                    return true;
                }
                case R.id.tab_menu: {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.home_ly, new MenuFragment())
                            .commit();
                    return true;
                }
                case R.id.tab_setting: {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.home_ly, new SettingFragment())
                            .commit();
                    return true;
                }
                case R.id.tab_logout: {
                    SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor autoLoginEdit = sharedPreferences.edit();
                    autoLoginEdit.clear();
                    autoLoginEdit.apply();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
            return false;
        }
    }
}