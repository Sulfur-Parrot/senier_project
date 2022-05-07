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

    // /*
    private Button Btn_livingroom;
    private Button Btn_innerroom;
    private Button Btn_lavatory;
    private Button Btn_kitchen;
    // */

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater,
                             @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);

        // /*
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

        Btn_innerroom = view.findViewById(R.id.btn_innerroom);
        Btn_innerroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                InnerroomFragment innerroomFragment = new InnerroomFragment();
                transaction.replace(R.id.framelayout, innerroomFragment);
                transaction.commit();
            }
        });

        Btn_lavatory = view.findViewById(R.id.btn_lavatory);
        Btn_lavatory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                LavatoryFragment lavatoryFragment = new LavatoryFragment();
                transaction.replace(R.id.framelayout, lavatoryFragment);
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
        // */


        return view;
    }
}