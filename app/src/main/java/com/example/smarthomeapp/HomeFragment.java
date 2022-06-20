package com.example.smarthomeapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;


public class HomeFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("프래그먼트", "홈 프래그먼트 실행");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        Button btn_119, btn_cpr, btn_safety, btn_refresh;
        TextView alert_room, alert_toilet, alert_kitchen;
        String[] alerts = new String[3];

        alert_room = rootView.findViewById(R.id.alert_room);
        alert_toilet = rootView.findViewById(R.id.alert_toilet);
        alert_kitchen = rootView.findViewById(R.id.alert_kitchen);

        btn_119 = (Button) rootView.findViewById(R.id.btn_119);
        btn_cpr = (Button) rootView.findViewById(R.id.btn_cpr);
        btn_safety = (Button) rootView.findViewById(R.id.btn_safety);
        btn_refresh = (Button) rootView.findViewById(R.id.btn_refresh);

        alerts[0] = "방: " + getArguments().getString("roomAlert");
        alerts[1] = "화장실: " + getArguments().getString("toiletAlert");
        alerts[2] = "주방: " + getArguments().getString("kitchenAlert");

        if(!alerts[0].equals("방: null")) alert_room.setText(alerts[0]);
        if(!alerts[1].equals("화장실: null")) alert_toilet.setText(alerts[1]);
        if(!alerts[2].equals("주방: null")) alert_kitchen.setText(alerts[2]);
        Log.d("프래그먼트", "프래그먼트로 값 들어옴");

        btn_119.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tel119 = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:119"));
                startActivity(tel119);
            }
        });

        btn_cpr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cpr = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.kacpr.org/page/page.php?category_idx=3&category1_code=1247206302&category2_code=1527742406&page_idx=1118"));
                startActivity(cpr);
            }
        });

        btn_safety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fall = new Intent(Intent.ACTION_VIEW, Uri.parse("https://easylaw.go.kr/CSP/CnpClsMainBtr.laf?popMenu=ov&csmSeq=690&ccfNo=2&cciNo=1&cnpClsNo=1"));
                startActivity(fall);
            }
        });

        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerts[0] = "방: " + getArguments().getString("roomAlert");
                alerts[1] = "화장실: " + getArguments().getString("toiletAlert");
                alerts[2] = "주방: " + getArguments().getString("kitchenAlert");

                if(!alerts[0].equals("방: null")) alert_room.setText(alerts[0]);
                if(!alerts[1].equals("화장실: null")) alert_toilet.setText(alerts[1]);
                if(!alerts[2].equals("주방: null")) alert_kitchen.setText(alerts[2]);
            }
        });

        return rootView;
    }
}