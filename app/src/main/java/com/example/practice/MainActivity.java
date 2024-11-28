package com.example.practice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
   private Button login;
   private Button signup;
//   private Button loginBypass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
                Boolean check = pref.getBoolean("flag",false);

                Intent iNext;
                if(check){ //for user logged in
                 iNext = new Intent(MainActivity.this, navigation.class);
                 startActivity(iNext);
                }

            }
        },0);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.firstPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

         //       TO SHIFT TO LOGIN ACTIVITY PAGE
         login = findViewById(R.id.loginButton);
         login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(MainActivity.this, Login.class);
                 startActivity(intent);
             }
         });

        //       TO SHIFT TO SIGNUP ACTIVITY PAGE
        signup = findViewById(R.id.signupButton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Signup.class);
                startActivity(intent);
            }
        });

//        loginBypass = findViewById(R.id.loginBypass);
//        loginBypass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,com.example.practice.navigation.class);
//                startActivity(intent);
//            }
//        });
    }


}