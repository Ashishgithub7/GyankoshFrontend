package com.example.practice;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class navigation extends AppCompatActivity {
    private static final int BACK_PRESS_INTERVAL = 2000; // 2 seconds
    private long lastBackPressTime = 0;
 BottomNavigationView navBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_navigation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.navMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        navBar=findViewById(R.id.navBar);
        navBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int id= item.getItemId();
               if (id==R.id.navHome){
                   loadFrag(new homeFragment(),true);
               } else if (id==R.id.navContactUs) {
                   loadFrag(new ContactUsFragment(),false);
               }else{
                   loadFrag(new profileFragment(),false);
               }
                return true;
            }
        });

        navBar.setSelectedItemId(R.id.navHome);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);
                if (currentFragment instanceof homeFragment) {
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastBackPressTime < BACK_PRESS_INTERVAL) {
                        finishAffinity(); // Close the app
                    } else {
                        lastBackPressTime = currentTime;
                        Toast.makeText(navigation.this, "Press back again to exit", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getSupportFragmentManager().popBackStack();
                }


            }
        });
    }

    public void loadFrag(Fragment fragment, boolean flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag)
            ft.add(R.id.container, fragment);
        else
            ft.replace(R.id.container, fragment);
        ft.addToBackStack(null); // Add to back stack to handle back navigation
        ft.commit();
    }

}