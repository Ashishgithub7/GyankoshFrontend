package com.example.practice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;


public class profileFragment extends Fragment {
    private TextView logOut, usernameTextView, emailTextView;
    private LinearLayout contactUs,AboutUs;
    private String email,username;

    public profileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences pref = requireActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        email = pref.getString("email",null);
        int index = email.indexOf("@");
        username = email.substring(0,index);

        usernameTextView = view.findViewById(R.id.username);
        usernameTextView.setText(username);

        emailTextView = view.findViewById(R.id.email);
        emailTextView.setText(email);

        contactUs = view.findViewById(R.id.contactUs);
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(),ContactUsFragment.class);
                startActivity(intent);

            }
        });

        logOut=view.findViewById(R.id.logout);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("flag",false);
                editor.apply();

                Intent intent = new Intent(requireContext(),com.example.practice.MainActivity.class);
                startActivity(intent);
            }

        });
        return view;
    }

}