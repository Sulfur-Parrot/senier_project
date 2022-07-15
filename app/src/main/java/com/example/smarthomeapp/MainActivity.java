package com.example.smarthomeapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    final String TAG = this.getClass().getSimpleName();

    private BackKeyHandler backKeyHandler = new BackKeyHandler(this);

    FrameLayout home_ly;
    BottomNavigationView bottomNavigationView;
    Fragment homeFragment, settingFragment;

    Bundle bundle = new Bundle();
    FragmentManager manager = getSupportFragmentManager();

    String[] alerts = new String[3];

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init(); //객체 정의
        SettingListener(); //리스너 등록

        //처음 시작할 탭 설정
        bottomNavigationView.setSelectedItemId(R.id.tab_home);

        //앱을 실행한 후 나가더라도 계속 돌아가는 부분
        Intent intent = new Intent(this, BackgroundService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        }

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
            FragmentTransaction transaction = manager.beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.tab_home: {
                    homeFragment = new HomeFragment();

                    SharedPreferences sharedPreferences = getSharedPreferences("alertTime", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    alerts[0] = sharedPreferences.getString("room", null);
                    alerts[1] = sharedPreferences.getString("toilet", null);
                    alerts[2] = sharedPreferences.getString("kitchen", null);

                    editor.clear();
                    editor.apply();

                    bundle.putString("roomAlert", alerts[0]);
                    bundle.putString("toiletAlert", alerts[1]);
                    bundle.putString("kitchenAlert", alerts[2]);

                    homeFragment.setArguments(bundle);

                    transaction.replace(R.id.home_ly, homeFragment).commit();

                    return true;
                }
                case R.id.tab_menu: {
                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(intent);
                    onPause();
                    break;
                }
                case R.id.tab_setting: {
                    settingFragment = new SettingFragment();
                    transaction.replace(R.id.home_ly, settingFragment).commit();
                    return true;
                }
            }
            return false;
        }
    }
}