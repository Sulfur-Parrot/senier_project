package com.example.smarthomeapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class HomeFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        String roomAl, toiletAl, kitchenAl;
        Button btn_119, btn_cpr, btn_safety, btn_fall;
        TextView alert_room, alert_toilet, alert_kitchen;

        alert_room = rootView.findViewById(R.id.alert_room);
        alert_toilet = rootView.findViewById(R.id.alert_toilet);
        alert_kitchen = rootView.findViewById(R.id.alert_kitchen);

        btn_119 = (Button) rootView.findViewById(R.id.btn_119);
        btn_cpr = (Button) rootView.findViewById(R.id.btn_cpr);
        btn_safety = (Button) rootView.findViewById(R.id.btn_safety);
        btn_fall = (Button) rootView.findViewById(R.id.btn_fall);

        /*
        roomAl = this.getArguments().getString("roomAlert");
        toiletAl = this.getArguments().getString("toiletAlert");
        kitchenAl = this.getArguments().getString("toiletAlert");

        alert_room.setText(roomAl);
        alert_toilet.setText(toiletAl);
        alert_kitchen.setText(kitchenAl);
        */

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

        btn_fall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fall = new Intent(Intent.ACTION_VIEW, Uri.parse("https://easylaw.go.kr/CSP/CnpClsMainBtr.laf?popMenu=ov&csmSeq=690&ccfNo=2&cciNo=1&cnpClsNo=1"));
                startActivity(fall);
            }
        });

        btn_safety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent safety = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.safekorea.go.kr/idsiSFK/neo/sfk/cs/contents/prevent/SDIJKM5138_LIST.html?menuSeq=128"));
                startActivity(safety);
            }
        });

        return rootView;
    }
}