package com.example.smarthomeapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    private Button  btn_room, btn_toilet, btn_kitchen, btn_logout;
    private TextView place;
    private ListView list_alert;
    BottomNavigationView bottomNavigationView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        btn_room = findViewById(R.id.btn_room);
        btn_toilet = findViewById(R.id.btn_toilet);
        btn_kitchen = findViewById(R.id.btn_kitchen);
        btn_logout = findViewById(R.id.btn_logout);
        place = findViewById(R.id.place);
        list_alert = findViewById(R.id.list_alert);

        btn_room.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                place.setText("방");

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println(response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray roomArray = jsonObject.getJSONArray("room_sensor");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                SensorMeasureRequest sensorMeasureRequest = new SensorMeasureRequest(responseListener);
                RequestQueue queue = Volley.newRequestQueue(MenuActivity.this);
                queue.add(sensorMeasureRequest);
            }
        });

        btn_toilet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                place.setText("화장실");

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println(response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray toiletArray = jsonObject.getJSONArray("toilet_sensor");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                SensorMeasureRequest sensorMeasureRequest = new SensorMeasureRequest(responseListener);
                RequestQueue queue = Volley.newRequestQueue(MenuActivity.this);
                queue.add(sensorMeasureRequest);
            }
        });

        btn_kitchen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                place.setText("주방");

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println(response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray kitchenArray = jsonObject.getJSONArray("kitchen_sensor");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                SensorMeasureRequest sensorMeasureRequest = new SensorMeasureRequest(responseListener);
                RequestQueue queue = Volley.newRequestQueue(MenuActivity.this);
                queue.add(sensorMeasureRequest);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
                SharedPreferences.Editor autoLoginEdit = sharedPreferences.edit();
                autoLoginEdit.clear();
                autoLoginEdit.apply();

                Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}