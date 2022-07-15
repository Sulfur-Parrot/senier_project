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


public class HomeFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("프래그먼트", "홈 프래그먼트 실행");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        Button btn_119, btn_cpr, btn_safety;
        TextView txt_where, txt_time, txt_countermeasure;
        String[] alerts = new String[3];

        txt_where = rootView.findViewById(R.id.txt_where);
        txt_time = rootView.findViewById(R.id.txt_time);
        txt_countermeasure = rootView.findViewById(R.id.txt_countermeasure);

        btn_119 = (Button) rootView.findViewById(R.id.btn_119);
        btn_cpr = (Button) rootView.findViewById(R.id.btn_cpr);
        btn_safety = (Button) rootView.findViewById(R.id.btn_safety);

        alerts[0] = getArguments().getString("roomAlert");
        alerts[1] = getArguments().getString("toiletAlert");
        alerts[2] = getArguments().getString("kitchenAlert");

        if(alerts[0] != null || alerts[1] != null || alerts[2] != null) {
            if (!alerts[0].equals("null")) {
                txt_where.setText("방");
                txt_time.setText(alerts[0]);
                txt_countermeasure.setText("낙상사고 감지!\n환자가 발생하였다면 119에 우선 신고를 하시고 척추 손상 등이 있을지 모르니 환자를 움직이게 하지 마시고 호흡과 혈액순환을 확인하세요. 호흡만 없다면 기도를 유지하고 인공호흡을 실시하여야 합니다. 호흡과 혈액순환이 모두 없는 상태라면 cpr을 시행하여야 합니다.");
            }
            else if (!alerts[1].equals("null")) {
                txt_where.setText("화장실");
                txt_time.setText(alerts[1]);
                txt_countermeasure.setText("낙상사고 감지!\n환자가 발생하였다면 119에 우선 신고를 하시고 척추 손상 등이 있을지 모르니 환자를 움직이게 하지 마시고 호흡과 혈액순환을 확인하세요.\n호흡만 없다면 기도를 유지하고 인공호흡을 실시하여야 합니다. 호흡과 혈액순환이 모두 없는 상태라면 cpr을 시행하여야 합니다.");
            }
            else if (!alerts[2].equals("null")) {
                txt_where.setText("주방");
                txt_time.setText(alerts[2]);
                txt_countermeasure.setText("가스누출 감지!\n점화 코크, 중간 벨브, 용기 벨브(도시가스는 메인벨브)를 잠그고 창문과 출입문 등을 열어 환기를 시켜주세요.(LPG용기에서 누출되는 경우 누출 최소화를 위해 얇은 천(수건 등)을 물에 적셔 누출부위에 붙여주세요) LPG는 공기보다 무거우므로 빗자루로 쓸어주듯이 밖으로 내보내야합니다.\n선풍기나 환풍기는 스파크를 일으킬 수 있으므로 사용하지 마시고, 주변의 불씨(전열기구 포함)를 멀리하세요.\n이후 가스 공급 업체나 도시가스 대행업소에 연락하여 안전조치 후에 사용해주세요.");
            }
        }

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

        return rootView;
    }
}