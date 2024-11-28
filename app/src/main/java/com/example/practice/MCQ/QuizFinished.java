package com.example.practice.MCQ;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practice.R;
import com.example.practice.navigation;

public class QuizFinished extends AppCompatActivity {
    private String finalFeedbackText;

    private TextView finalFeedback;
     private Button homeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_finished);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        homeBtn=findViewById(R.id.home);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizFinished.this, navigation.class);
                startActivity(intent);
            }
        });
        finalFeedbackText=  getIntent().getStringExtra("finalFeedback").toString();
        finalFeedback = findViewById(R.id.finalFeedback);
        if (finalFeedbackText != null) {
            finalFeedback.setText(finalFeedbackText);
        } else {
            finalFeedback.setText("No feedback provided.");
        }
    }

    @Override
    public void onBackPressed() {
        // Navigate to the home screen or main activity
        super.onBackPressed();
        Intent intent = new Intent(this, navigation.class); // Replace with your home screen activity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Optional: finish current activity to remove it from the stack
}
//    
}