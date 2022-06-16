package com.example.smarthomeapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    final String TAG = this.getClass().getSimpleName();

    private BackKeyHandler backKeyHandler = new BackKeyHandler(this);

    LinearLayout home_ly;
    BottomNavigationView bottomNavigationView;

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

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                System.out.println(response);
                                JSONObject jsonObject = new JSONObject(response);


                                JSONArray roomArray = jsonObject.getJSONArray("room_sensor");
                                JSONObject roomObject = roomArray.getJSONObject(9);
                                String room_r = roomObject.getString("result");

                                JSONArray toiletArray = jsonObject.getJSONArray("toilet_sensor");
                                JSONObject toiletObject = toiletArray.getJSONObject(9);
                                String toilet_r = toiletObject.getString("result");

                                JSONArray kitchenArray = jsonObject.getJSONArray("kitchen_sensor");
                                JSONObject kitchenObject = kitchenArray.getJSONObject(9);
                                String kitchen_r = kitchenObject.getString("result");



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    SensorMeasureRequest sensorMeasureRequest = new SensorMeasureRequest(responseListener);
                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    queue.add(sensorMeasureRequest);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
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
                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
                case R.id.tab_setting: {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.home_ly, new SettingFragment())
                            .commit();
                    return true;
                }
            }
            return false;
        }
    }
}