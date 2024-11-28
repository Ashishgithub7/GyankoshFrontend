package com.example.practice.Notes;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;


import com.example.practice.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class NotesMain extends AppCompatActivity {
    private TextView featureView;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private String course,feature;
    private TextView courseName;
    private List<String> subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notes_main);

        feature = getIntent().getStringExtra("feature");
        course = getIntent().getStringExtra("COURSE_NAME");

        featureView = findViewById(R.id.feature);
        featureView.setText(feature);
//        courseName = findViewById(R.id.courseName);
//        courseName.setText(course);

        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewpager);

        subjects = new ArrayList<>();
        switch (course) {
            case "IOE":
                subjects.add("Physics");
                subjects.add("Chemistry");
                subjects.add("Maths");
                subjects.add("English");
                break;
            case "IOM":
                subjects.add("Physics");
                subjects.add("Chemistry");
                subjects.add("English");
                subjects.add("Biology");
                break;
            case "+2 LEVEL":
                subjects.add("Science");
                subjects.add("S.Maths");
                subjects.add("S.English");
                subjects.add("G.K");
                break;
            // Add more courses and subjects as needed
            default:
                subjects.add("सामान्य ज्ञान ");
                subjects.add("Intelligent Question");
                break;
        }

        // Initialize the adapter with the FragmentActivity
        NoteViewPagerAdapter adapter = new NoteViewPagerAdapter(this,subjects,feature);
        viewPager.setAdapter(adapter);

        // Set up TabLayout with ViewPager2 using TabLayoutMediator
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            // Set the tab text based on the subject list
            if (position < subjects.size()) {
                tab.setText(subjects.get(position));
            }
        }).attach();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}