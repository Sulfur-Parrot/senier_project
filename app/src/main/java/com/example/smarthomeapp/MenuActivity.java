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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    SingerAdapter adapter;

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

        //리스트뷰를 위한 어댑터
        ListView listView = (ListView) findViewById(R.id.list_alert);
        adapter = new SingerAdapter();

        adapter.addItem(new SingerItem("","",R.drawable.));
        //리스트 뷰에 어댑터 설정
        listView.setAdapter(adapter);
        //이벤트 처리 리스너 설정
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "발생위치 : " + item.getPlace(), Toast.LENGTH_LONG).show();
            }
        }); */

        //버튼 눌렀을 때 우측 이름, 발생 시간이 리스트뷰에 포함되도록 처리
        //Button button = (Button) findViewById()
        //어댑터 끝
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

    class SingerAdapter extends BaseAdapter{
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position){
            return items.get(position);
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //뷰 객체 재사용
         SingerItemView view = null;
         if (convertView == null) {
             view = new SingerItemView(getApplicationContext());
         } else {
             view = (SingerItemView) convertView;
         }

         SingerItem item = items.get(position);

         view.setPlace(item.getPlace());
         view.setTime(item.getTime());
         view.setImage(item.getResID());

         return view;

        }

    }

}