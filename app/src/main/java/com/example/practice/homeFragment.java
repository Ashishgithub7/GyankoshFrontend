package com.example.practice;

import static android.content.Intent.getIntent;
import static androidx.core.app.ActivityCompat.finishAffinity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class homeFragment extends Fragment {
    private TextView greetTextView;
    private ImageView loksewa;
    private ImageView ioe;
    private ImageView iom;
    private ImageView plus2;
    private ImageView notification;
    private String email,username,greetMessage;
    public homeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences pref = requireActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        Long userId = pref.getLong("userId",0);
        email = pref.getString("email","android@gmail.com");
        int index= email.indexOf("@");
        username = email.substring(0,index);

        loksewa=view.findViewById(R.id.loksewaImg);
        loksewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoksewaFragment loksewaFragment = new LoksewaFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, loksewaFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        ioe=view.findViewById(R.id.ioeImg);
        ioe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IoeFragment ioeFragment = new IoeFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, ioeFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        iom=view.findViewById(R.id.iomImg);
        iom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IomFragment iomFragment = new IomFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, iomFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        plus2=view.findViewById(R.id.plus2Img);
        plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Plus2Fragment plus2Fragment = new Plus2Fragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, plus2Fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

//        notification=view.findViewById(R.id.notificationIcon);
//        notification.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NotificationFragment notificationFragment = new NotificationFragment();
//                getParentFragmentManager().beginTransaction()
//                        .replace(R.id.container, notificationFragment)
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });

        greetTextView = view.findViewById(R.id.greet);
        greetTextView.setText("Hello, " + username + "!");
    return view;


    }



}