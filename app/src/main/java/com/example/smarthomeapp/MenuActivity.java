package com.example.smarthomeapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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
    private Button  btn_room, btn_toilet, btn_kitchen;
    private LineChart sensor_chart;
    BottomNavigationView bottomNavigationView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        SettingListener();

        btn_room = findViewById(R.id.btn_room);
        btn_toilet = findViewById(R.id.btn_toilet);
        btn_kitchen = findViewById(R.id.btn_kitchen);
        sensor_chart = findViewById(R.id.sensor_chart);

        btn_room.setOnClickListener(new View.OnClickListener() {

            Float room_v;

            @Override
            public void onClick(View view) {
                sensor_chart.invalidate();
                sensor_chart.clear();
                /*
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                */
                List<Entry> entries = new ArrayList<>();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println(response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray roomArray = jsonObject.getJSONArray("room_sensor");


                            for (int i = 0; i < 10; i ++) {
                                JSONObject roomObject = roomArray.getJSONObject(i);
                                String vib_room = roomObject.getString("vibaration");
                                room_v = Float.parseFloat(vib_room);
                                entries.add(new Entry(i+1, room_v));
                            }

                            LineDataSet lineDataSet = new LineDataSet(entries, "Vibration");
                            lineDataSet.setLineWidth(2);
                            lineDataSet.setCircleRadius(6);
                            lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
                            lineDataSet.setCircleHoleColor(Color.BLACK);
                            lineDataSet.setColor(Color.BLUE);
                            lineDataSet.setDrawCircleHole(true);
                            lineDataSet.setDrawCircles(true);
                            lineDataSet.setDrawHorizontalHighlightIndicator(false);
                            lineDataSet.setDrawHighlightIndicators(false);
                            lineDataSet.setDrawValues(false);

                            LineData lineData = new LineData(lineDataSet);
                            sensor_chart.setData(lineData);

                            XAxis xAxis = sensor_chart.getXAxis();
                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                            xAxis.setTextColor(Color.BLACK);
                            xAxis.enableGridDashedLine(8, 24, 0);

                            YAxis yLAxis = sensor_chart.getAxisLeft();
                            yLAxis.setTextColor(Color.BLACK);

                            YAxis yRAxis = sensor_chart.getAxisRight();
                            yRAxis.setDrawLabels(false);
                            yRAxis.setDrawAxisLine(false);
                            yRAxis.setDrawGridLines(false);

                            Description description = new Description();
                            description.setText("");

                            sensor_chart.setDoubleTapToZoomEnabled(false);
                            sensor_chart.setDrawGridBackground(false);
                            sensor_chart.setDragYEnabled(true);
                            sensor_chart.setDescription(description);
                            sensor_chart.invalidate();

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

            Float toilet_v, toilet_m;

            @Override
            public void onClick(View view) {
                sensor_chart.invalidate();
                sensor_chart.clear();

                List<Entry> entries_vib = new ArrayList<>();
                List<Entry> entries_move = new ArrayList<>();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println(response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray toiletArray = jsonObject.getJSONArray("toilet_sensor");


                            for (int i = 0; i < 10; i ++) {
                                JSONObject toiletObject = toiletArray.getJSONObject(i);

                                String vib_toilet = toiletObject.getString("vibaration");
                                toilet_v = Float.parseFloat(vib_toilet);
                                entries_vib.add(new Entry(i+1, toilet_v));

                                String move_toilet = toiletObject.getString("movement");
                                toilet_m = Float.parseFloat(move_toilet);
                                entries_move.add(new Entry(i+1, toilet_m * 300));
                            }
                            LineData linedata = new LineData();

                            //진동 센서
                            LineDataSet lineDataSet_vib = new LineDataSet(entries_vib, "Vibration");
                            lineDataSet_vib.setLineWidth(2);
                            lineDataSet_vib.setCircleRadius(4);
                            lineDataSet_vib.setColor(Color.BLUE);
                            lineDataSet_vib.setCircleColor(Color.parseColor("#FFA1B4DC"));
                            lineDataSet_vib.setCircleHoleColor(Color.BLACK);
                            lineDataSet_vib.setDrawCircleHole(true);
                            lineDataSet_vib.setDrawCircles(true);
                            lineDataSet_vib.setDrawHorizontalHighlightIndicator(false);
                            lineDataSet_vib.setDrawHighlightIndicators(false);
                            lineDataSet_vib.setDrawValues(false);

                            linedata.addDataSet(lineDataSet_vib);

                            //움직임 감지 센서
                            LineDataSet lineDataSet_move = new LineDataSet(entries_move, "Movement");
                            lineDataSet_move.setLineWidth(2);
                            lineDataSet_move.setCircleRadius(4);
                            lineDataSet_move.setColor(Color.RED);
                            lineDataSet_move.setCircleColor(Color.parseColor("#FFA1B4DC"));
                            lineDataSet_move.setCircleHoleColor(Color.BLACK);
                            lineDataSet_move.setDrawCircleHole(true);
                            lineDataSet_move.setDrawCircles(true);
                            lineDataSet_move.setDrawHorizontalHighlightIndicator(false);
                            lineDataSet_move.setDrawHighlightIndicators(false);
                            lineDataSet_move.setDrawValues(false);

                            linedata.addDataSet(lineDataSet_move);


                            XAxis xAxis = sensor_chart.getXAxis();
                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                            xAxis.setTextColor(Color.BLACK);
                            xAxis.enableGridDashedLine(8, 24, 0);

                            YAxis yLAxis = sensor_chart.getAxisLeft();
                            yLAxis.setTextColor(Color.BLACK);

                            YAxis yRAxis = sensor_chart.getAxisRight();
                            yRAxis.setDrawLabels(false);
                            yRAxis.setDrawAxisLine(false);
                            yRAxis.setDrawGridLines(false);

                            Description description = new Description();
                            description.setText("");

                            sensor_chart.setData(linedata);
                            sensor_chart.setDoubleTapToZoomEnabled(false);
                            sensor_chart.setDrawGridBackground(false);
                            sensor_chart.setDragYEnabled(true);
                            sensor_chart.setDescription(description);
                            sensor_chart.invalidate();

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

            Float kitchen_gas1, kitchen_gas2, kitchen_gas3;

            @Override
            public void onClick(View view) {
                sensor_chart.invalidate();
                sensor_chart.clear();

                List<Entry> entries_gas1 = new ArrayList<>();
                List<Entry> entries_gas2 = new ArrayList<>();
                List<Entry> entries_gas3 = new ArrayList<>();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println(response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray kitchenArray = jsonObject.getJSONArray("kitchen_sensor");


                            for (int i = 0; i < 10; i ++) {
                                JSONObject kitchenObject = kitchenArray.getJSONObject(i);

                                String gas1_kitchen = kitchenObject.getString("Mq2");
                                kitchen_gas1 = Float.parseFloat(gas1_kitchen);
                                entries_gas1.add(new Entry(i+1, kitchen_gas1));

                                String gas2_kitchen = kitchenObject.getString("Mq5");
                                kitchen_gas2 = Float.parseFloat(gas2_kitchen);
                                entries_gas2.add(new Entry(i+1, kitchen_gas2));

                                String gas3_kitchen = kitchenObject.getString("Mq7");
                                kitchen_gas3 = Float.parseFloat(gas3_kitchen);
                                entries_gas3.add(new Entry(i+1, kitchen_gas3));
                            }
                            LineData linedata = new LineData();

                            //가스센서1
                            LineDataSet lineDataSet_gas01 = new LineDataSet(entries_gas1, "Gas01");
                            lineDataSet_gas01.setLineWidth(2);
                            lineDataSet_gas01.setCircleRadius(4);
                            lineDataSet_gas01.setColor(Color.BLUE);
                            lineDataSet_gas01.setCircleColor(Color.parseColor("#FFA1B4DC"));
                            lineDataSet_gas01.setCircleHoleColor(Color.BLACK);
                            lineDataSet_gas01.setDrawCircleHole(true);
                            lineDataSet_gas01.setDrawCircles(true);
                            lineDataSet_gas01.setDrawHorizontalHighlightIndicator(false);
                            lineDataSet_gas01.setDrawHighlightIndicators(false);
                            lineDataSet_gas01.setDrawValues(false);

                            linedata.addDataSet(lineDataSet_gas01);

                            //가스센서2
                            LineDataSet lineDataSet_gas02 = new LineDataSet(entries_gas2, "Gas02");
                            lineDataSet_gas02.setLineWidth(2);
                            lineDataSet_gas02.setCircleRadius(4);
                            lineDataSet_gas02.setColor(Color.RED);
                            lineDataSet_gas02.setCircleColor(Color.parseColor("#FFA1B4DC"));
                            lineDataSet_gas02.setCircleHoleColor(Color.BLACK);
                            lineDataSet_gas02.setDrawCircleHole(true);
                            lineDataSet_gas02.setDrawCircles(true);
                            lineDataSet_gas02.setDrawHorizontalHighlightIndicator(false);
                            lineDataSet_gas02.setDrawHighlightIndicators(false);
                            lineDataSet_gas02.setDrawValues(false);

                            linedata.addDataSet(lineDataSet_gas02);

                            //가스센서3
                            LineDataSet lineDataSet_gas03 = new LineDataSet(entries_gas3, "Gas03");
                            lineDataSet_gas03.setLineWidth(2);
                            lineDataSet_gas03.setCircleRadius(4);
                            lineDataSet_gas03.setColor(Color.YELLOW);
                            lineDataSet_gas03.setCircleColor(Color.parseColor("#FFA1B4DC"));
                            lineDataSet_gas03.setCircleHoleColor(Color.BLACK);
                            lineDataSet_gas03.setDrawCircleHole(true);
                            lineDataSet_gas03.setDrawCircles(true);
                            lineDataSet_gas03.setDrawHorizontalHighlightIndicator(false);
                            lineDataSet_gas03.setDrawHighlightIndicators(false);
                            lineDataSet_gas03.setDrawValues(false);

                            linedata.addDataSet(lineDataSet_gas03);


                            XAxis xAxis = sensor_chart.getXAxis();
                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                            xAxis.setTextColor(Color.BLACK);
                            xAxis.enableGridDashedLine(8, 24, 0);

                            YAxis yLAxis = sensor_chart.getAxisLeft();
                            yLAxis.setTextColor(Color.BLACK);

                            YAxis yRAxis = sensor_chart.getAxisRight();
                            yRAxis.setDrawLabels(false);
                            yRAxis.setDrawAxisLine(false);
                            yRAxis.setDrawGridLines(false);

                            Description description = new Description();
                            description.setText("");

                            sensor_chart.setData(linedata);
                            sensor_chart.setDoubleTapToZoomEnabled(false);
                            sensor_chart.setDrawGridBackground(false);
                            sensor_chart.setDragYEnabled(true);
                            sensor_chart.setDescription(description);
                            sensor_chart.invalidate();
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

    }

    private void SettingListener() {
        //선택 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new MenuActivity.TabSelectedListener());
    }


    class TabSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.tab_backhome: {
                    Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
            return false;
        }
    }
}