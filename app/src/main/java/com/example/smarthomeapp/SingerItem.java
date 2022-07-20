package com.example.smarthomeapp;

public class SingerItem {
    String place;
    String time;
    int resID;

    public SingerItem(String place, String time, int resID){
        this.place = place;
        this.time = time;
        this.resID = resID;
    }

    //getter setter
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getResID(){
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    //아이템 문자열로 출력

    @Override
    public String toString(){
        return "SingerItem{" + "발생위치='" + place + '\'' +", 발생시간='" + time + '\'' + '}';
    }
}

