package com.example.practice.MCQ;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practice.Api.ApiClient;
import com.example.practice.Api.ApiService;
import com.example.practice.Entity.McqQuestionResponse;
import com.example.practice.R;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class McqFirstPage extends AppCompatActivity {

    private TextView rule;
    private Button mcqBtn;
    private ApiService apiService;
    private String course;
    private String questionText;
    private String optionA,optionB, optionC, optionD, feedback,correctAnswer;
    private Long userId;
    private String userStringId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq_first_page);
        course = getIntent().getStringExtra("COURSE_NAME").toString();
        TextView courseName=findViewById(R.id.courseName);
        courseName.setText(course);
        SharedPreferences pref = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        userId = pref.getLong("userId",0);
        userStringId=userId.toString();
        Toast.makeText(this, "userId: "+userId, Toast.LENGTH_SHORT).show();



//        mcqBtn.setOnClickListener(v -> startQuiz());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
            });
        rule=findViewById(R.id.mcqRule);
        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(McqFirstPage.this, McqRules.class);
                startActivity(intent);
            }
        });

        mcqBtn = findViewById(R.id.startMcq);
        mcqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuizs();
//                Intent intent= new Intent(McqFirstPage.this,com.example.practice.MCQ.McqMain.class);
//                    intent.putExtra("course",course);
//                    intent.putExtra("questionText",questionText);
//                    intent.putExtra("optionA",optionA);
//                    intent.putExtra("optionB",optionB);
//                    intent.putExtra("optionC",optionC);
//                    intent.putExtra("optionD",optionD);
//                    intent.putExtra("feedback",feedback);
//                    intent.putExtra("correctAnswer",correctAnswer);
//
//                startActivity(intent);

            }
        });


    }private void startQuizs(){
        apiService = ApiClient.getClient().create(ApiService.class);

        Call<McqQuestionResponse> call= apiService.startQuiz(course,userId);
//        Call<ApiResponse> call = apiService.signup(signupRequest);
        call.enqueue(new Callback<McqQuestionResponse>() {
            @Override
            public void onResponse(Call<McqQuestionResponse> call, Response<McqQuestionResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                   Intent intent= new Intent(McqFirstPage.this, McqMain.class);
//                    intent.putExtra("course",course);
                   intent.putExtra("mcqQuestion",response.body());
                   startActivity(intent);
//                    McqQuestionResponse question = response.body();
//                   questionText = question.getQuestionText();
//                   optionA = question.getOptionA();
//                   optionB = question.getOptionB();
//                   optionC = question.getOptionC();
//                   optionD = question.getOptionD();
//                   feedback = question.getFeedback();
//                   correctAnswer = question.getCorrectAnswer();
//                   if (question==null)Toast.makeText(McqFirstPage.this, "data not found", Toast.LENGTH_SHORT).show();
//                   else Toast.makeText(McqFirstPage.this, questionText, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(McqFirstPage.this,"Failed to get the question.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<McqQuestionResponse> call, Throwable t) {
                Snackbar.make(findViewById(R.id.main), "error: "+ t.getMessage(), Snackbar.LENGTH_SHORT).show();
            }

        });

    }
}