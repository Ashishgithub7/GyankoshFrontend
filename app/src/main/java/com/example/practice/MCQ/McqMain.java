package com.example.practice.MCQ;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practice.Api.ApiClient;
import com.example.practice.Api.ApiService;
import com.example.practice.Entity.McqQuestionResponse;
import com.example.practice.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class McqMain extends AppCompatActivity {

    private int originalTopMargin;
    private MaterialCardView feedbackCard;
    private McqQuestionResponse mcqQuestion;
    private ApiService apiService;
    private TextView questionBox, feedbackBox, questionNumber,timer;
//    private long mTimeMilliLeft= 45*60*1000;
    private CountDownTimer countDownTimer;

    private Button optionABtn, optionBBtn, optionCBtn, optionDBtn, skipBtn;

    private String course, questionText ,optionAText, optionBText,optionDText,optionCText,feedbackText,correctAnswerText;
    private String finalFeedbackText ="finalFeedback";
    private boolean quizComplete;
    private String userAnswer;
    private Long userId;

    private  int i =2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mcq_main);
        SharedPreferences pref = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        userId = pref.getLong("userId",0);

//        countDownTimer = new CountDownTimer(mTimeMilliLeft,1000) {
//            @Override
//            public void onTick(long l) {
//                mTimeMilliLeft = l;
//                updateCountDownTimer();
//            }

//            @Override
//            public void onFinish() {
//                String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", 00,00);
//                timer.setText(timeLeftFormatted);
//            }
//        }.start();

        feedbackCard = findViewById(R.id.feedbackCard);
        feedbackCard.setVisibility(View.GONE);

        mcqQuestion =(McqQuestionResponse) getIntent().getSerializableExtra("mcqQuestion");
        questionText = mcqQuestion.getQuestionText();
        optionAText = mcqQuestion.getOptionA();
        optionBText = mcqQuestion.getOptionB();
        optionCText = mcqQuestion.getOptionC();
        optionDText = mcqQuestion.getOptionD();
        correctAnswerText = mcqQuestion.getCorrectAnswer();
        feedbackText = mcqQuestion.getFeedback();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        questionBox = findViewById(R.id.question);
        feedbackBox = findViewById(R.id.feedback);
        optionABtn = findViewById(R.id.optionA);
        optionBBtn = findViewById(R.id.optionB);
        optionCBtn = findViewById(R.id.optionC);
        optionDBtn = findViewById(R.id.optionD);
        skipBtn = findViewById(R.id.skip);
        questionNumber = findViewById(R.id.questionNumber);
        timer = findViewById(R.id.timer);

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) skipBtn.getLayoutParams();
        originalTopMargin = params.topMargin;

        setTexts();


        optionABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOptionClick(optionABtn, correctAnswerText);
//                changeConstraint();

            }
        });optionBBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOptionClick(optionBBtn, correctAnswerText);
//                changeConstraint();
            }
        });optionCBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOptionClick(optionCBtn, correctAnswerText);
//                changeConstraint();
            }
        });optionDBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handleOptionClick(optionDBtn, correctAnswerText);
//                changeConstraint();
            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

//    private void updateCountDownTimer() {
//        int minutes = (int)(mTimeMilliLeft/1000)/60;
//        int seconds = (int)(mTimeMilliLeft/1000)%60;
//        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
//        timer.setText(timeLeftFormatted);
//    }

    private void handleOptionClick(Button btn, String text) {
        userAnswer = btn.getText().toString();
        disableAllOptions();

        if (btn.getText().equals(text)) {
            btn.setBackgroundColor(ContextCompat.getColor(this, R.color.third));
            new Handler().postDelayed(() -> {
                feedbackCard.setVisibility(View.VISIBLE);
                moveSkipBtn();
            }, 200); // Delay for 1 second
        } else {
            btn.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
            new Handler().postDelayed(() -> {
                feedbackCard.setVisibility(View.VISIBLE);
                moveSkipBtn();
            }, 200); // Delay for 1 second
        }

    }private void disableAllOptions() {
        optionABtn.setClickable(false);
        optionBBtn.setClickable(false);
        optionCBtn.setClickable(false);
        optionDBtn.setClickable(false);
    }
    private void moveSkipBtn(){
        // Get the current layout parameters of the button
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) skipBtn.getLayoutParams();
        // Increase the top margin (e.g., by 250 pixels)
        params.topMargin += 250;
        // Apply the updated layout parameters to the button
        skipBtn.setLayoutParams(params);
    }
    private void submit(){
        apiService = ApiClient.getClient().create(ApiService.class);

        Call<McqQuestionResponse> call= apiService.submitAnswer(userAnswer,userId);

        call.enqueue(new Callback<McqQuestionResponse>() {
            @Override
            public void onResponse(Call<McqQuestionResponse> call, Response<McqQuestionResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    resetOptions();
                McqQuestionResponse question = response.body();
                questionText = question.getQuestionText();
                optionAText = question.getOptionA();
                optionBText = question.getOptionB();
                optionCText = question.getOptionC();
                optionDText = question.getOptionD();
                correctAnswerText = question.getCorrectAnswer();
                feedbackText = question.getFeedback();
                finalFeedbackText =question.getFinalFeedback();
                quizComplete = question.isQuizComplete();
                if(!quizComplete){

                        String text = "Question "+i+"/50";
                        questionNumber.setText(text);
                        setTexts();
                        i++;
                }else{
                    quizFinished();
                }
                }else{
                    Toast.makeText(McqMain.this,"Failed to get the question.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<McqQuestionResponse> call, Throwable t) {
                Snackbar.make(findViewById(R.id.main), "error: "+ t.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
    private void setTexts(){
        questionBox.setText(questionText);
        optionABtn.setText(optionAText);
        optionBBtn.setText(optionBText);
        optionCBtn.setText(optionCText);
        optionDBtn.setText(optionDText);
        feedbackBox.setText(feedbackText);

    }
    private void resetOptions() {
        // Reset the background color of the buttons
        optionABtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        optionBBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        optionCBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        optionDBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));

        // Re-enable clickability
        optionABtn.setClickable(true);
        optionBBtn.setClickable(true);
        optionCBtn.setClickable(true);
        optionDBtn.setClickable(true);

        // Hide the feedback card and reset the skip button position if needed
        feedbackCard.setVisibility(View.GONE);
        resetSkipBtnPosition();
    }
    private void resetSkipBtnPosition() {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) skipBtn.getLayoutParams();
        params.topMargin = originalTopMargin;
        skipBtn.setLayoutParams(params);
    }
    private void quizFinished(){
        Intent intent = new Intent(McqMain.this, QuizFinished.class);
        intent.putExtra("finalFeedback",finalFeedbackText);
        startActivity(intent);
    }

}