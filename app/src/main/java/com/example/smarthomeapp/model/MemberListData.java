package com.example.smarthomeapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MemberListData extends BaseResponse {
    @SerializedName("data") public ArrayList<Member> memberList;
}