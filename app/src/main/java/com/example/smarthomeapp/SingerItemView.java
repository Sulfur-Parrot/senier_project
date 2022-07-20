package com.example.smarthomeapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SingerItemView extends LinearLayout {
    TextView textView1;
    TextView textView2;
    ImageView imageView;


    //생성 > Constructor

    public SingerItemView(Context context){
        super(context);

        init(context);
    }

    public SingerItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item, this, true);

        textView1 = (TextView) findViewById(R.id.textview1);
        textView2 = (TextView) findViewById(R.id.textview2);
        imageView = (ImageView) findViewById(R.id.imageView);

    }

    public void setPlace(String place){
        textView1.setText(place);
    }

    public void setTime(String time){
        textView2.setText(time);
    }

    public void setImage(int resId){
        imageView.setImageResource(resId);
    }

}
