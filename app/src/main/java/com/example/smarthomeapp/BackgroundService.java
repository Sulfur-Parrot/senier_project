package com.example.smarthomeapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;

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

    String CHANNEL_ID = "CHANNEL_ID";
    String result1 = "0", result2 = "0";
    String r , k , t ;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent == null){
            return START_STICKY;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel =
                    new NotificationChannel(CHANNEL_ID, "알림 설정 모드 타이틀", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            assert manager != null;
            manager.createNotificationChannel(serviceChannel);
        }

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("스마트홈 알림")
                .setContentText("센서에서 비정상적인 값이 측정될 경우 알림이 울립니다.")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);
        new Thread(new Runnable() {

            @Override
            public void run() {
                //여기에 지속적으로 돌아가야할 작업을 넣는다

                while(true) {
                    r = "";
                    k = "";
                    t = "";
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                result2 = result1;
                                // System.out.println(response);
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

                                if (room.equals("1") || kitchen.equals("1") || toilet.equals("1")) {
                                    //System.out.println("!!경고!! " + " 방: " + room + " 주방: " + kitchen + " 화장실: " + toilet);
                                    if(room.equals("1")) {r = "방";}
                                    if(kitchen.equals("1")) {k = "주방";}
                                    if(toilet.equals("1")){t = "화장실";}

                                    result1 = "1";
                                    if (result2.equals("0")) {sendNotification(r, k, t);}

                                } else {
                                    result1 = "0";
                                }

                                Thread.sleep(1000);
                            } catch (JSONException | InterruptedException e ) {
                                e.printStackTrace();
                            }
                        }
                    };

                    SensorMeasureRequest sensorMeasureRequest = new SensorMeasureRequest(responseListener);
                    RequestQueue queue = Volley.newRequestQueue(BackgroundService.this);
                    queue.add(sensorMeasureRequest);
                }
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    public BackgroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void sendNotification(String a, String b, String c) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        builder.setSmallIcon(R.drawable.ic_siren)
                .setContentTitle("경고!")
                .setContentText("비정상적인 수치가 감지되었습니다!("+r+" "+k+" "+t+")")
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }
}

