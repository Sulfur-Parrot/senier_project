package com.example.smarthomeapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MenuFragment extends Fragment {
    private View view;

    private Button Btn_livingroom;
    private Button Btn_room;
    private Button Btn_toilet;
    private Button Btn_kitchen;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater,
                             @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);

        Btn_livingroom = view.findViewById(R.id.btn_livingroom);
        Btn_livingroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                LivingroomFragment livingroomFragment = new LivingroomFragment();
                transaction.replace(R.id.framelayout, livingroomFragment);
                transaction.commit();
            }
        });

        Btn_room = view.findViewById(R.id.btn_room);
        Btn_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                RoomFragment roomFragment = new RoomFragment();
                transaction.replace(R.id.framelayout, roomFragment);
                transaction.commit();
            }
        });

        Btn_toilet = view.findViewById(R.id.btn_toilet);
        Btn_toilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ToiletFragment toiletFragment = new ToiletFragment();
                transaction.replace(R.id.framelayout, toiletFragment);
                transaction.commit();
            }
        });

        Btn_kitchen = view.findViewById(R.id.btn_kitchen);
        Btn_kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                KitchenFragment kitchenFragment = new KitchenFragment();
                transaction.replace(R.id.framelayout, kitchenFragment);
                transaction.commit();
            }
        });


        return view;
    }
}