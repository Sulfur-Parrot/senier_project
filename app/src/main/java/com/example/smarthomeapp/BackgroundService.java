package com.example.smarthomeapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;


public class BackgroundService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent == null){
            return START_STICKY;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel =
                    new NotificationChannel("CHANNEL_ID", "알림 설정 모드 타이틀", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            assert manager != null;
            manager.createNotificationChannel(serviceChannel);
        }

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setContentTitle("알림 타이틀")
                .setContentText("알림 설명")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);
        Handler handler = new Handler();
        new Thread(new Runnable() {

            @Override
            public void run() {
                //여기에 지속적으로 돌아가야할 작업을 넣는다
                while(true) {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                System.out.println(response);
                                JSONObject jsonObject = new JSONObject(response);

                                //room_sensor
                                JSONArray roomArray = jsonObject.getJSONArray("room_sensor");
                                JSONObject roomObject = roomArray.getJSONObject(0);
                                String room = roomObject.getString("result");
                                //kitchen_sensor
                                JSONArray kitchenArray = jsonObject.getJSONArray("kitchen_sensor");
                                JSONObject kitchenObject = kitchenArray.getJSONObject(0);
                                String kitchen = kitchenObject.getString("result");
                                //toilet_sensor
                                JSONArray toiletArray = jsonObject.getJSONArray("toilet_sensor");
                                JSONObject toiletObject = toiletArray.getJSONObject(0);
                                String toilet = toiletObject.getString("result");


                                if (room.equals("0") || kitchen.equals("0") || toilet.equals("0")) {
                                    //System.out.println("~평상시~ " + " 방: " + room + " 주방: " + kitchen + " 화장실: " + toilet);
                                } else {
                                    //System.out.println("!!경고!!" + " 방: " + room + " 주방: " + kitchen + " 화장실: " + toilet);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    SensorResultRequest sensorResultRequest = new SensorResultRequest(responseListener);
                    RequestQueue queue = Volley.newRequestQueue(BackgroundService.this);
                    queue.add(sensorResultRequest);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    public BackgroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

