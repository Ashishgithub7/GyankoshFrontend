package com.example.practice.Model_Test;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practice.MCQ.QuizFinished;
import com.example.practice.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ModelTestActivity extends AppCompatActivity {
    private ConstraintLayout header;
    private String modelSetName;
    private TextView modelSetNameView,timer;
    private ListView listView;
    private ModelTestQuestionAdapter questionAdapter;
    private List<ModelSetQuestions> fullQuestionList = new ArrayList<>();
    private ArrayList<String> selectedAnswers;
    private int currentIndex = 0;
    private float score = 0;
    private long mTimeMilliLeft;
    private CountDownTimer countDownTimer;
    private String courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_model_test);
        fullQuestionList = getIntent().getParcelableArrayListExtra("questionList");
        courseName=getIntent().getStringExtra("COURSE_NAME");


        if (courseName.equals("IOM")) {
            mTimeMilliLeft = 4 * 60 * 1000; //4 minutes
        } else {
            mTimeMilliLeft = 45 * 60 * 1000; //45 minutes
        }

        header = findViewById(R.id.Header);
        timer = findViewById(R.id.timer);
        countDownTimer = new CountDownTimer(mTimeMilliLeft,1000) {
            @Override
            public void onTick(long l) {
                mTimeMilliLeft = l;
                updateCountDownTimer();

                if (mTimeMilliLeft <= 10000) { // 10 seconds in milliseconds
                    header.setBackgroundColor(ContextCompat.getColor(ModelTestActivity.this, R.color.timeUp));
                }
            }

            @Override
            public void onFinish() {
                // Called when time comes down to 00:00
                String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", 00,00);
                timer.setText(timeLeftFormatted);
                listView.setBackgroundColor(ContextCompat.getColor(ModelTestActivity.this, R.color.timeUp));
                new Handler().postDelayed(() -> {

                showFinalScore();
                }, 200);
                }

        }.start();

        listView = findViewById(R.id.questionListViewArjun);
        modelSetName = getIntent().getStringExtra("modelSetName");

        if (fullQuestionList == null) {
            Toast.makeText(this, "Question list is null", Toast.LENGTH_SHORT).show();
        } else if (fullQuestionList.isEmpty()) {
            Toast.makeText(this, "Full question list is empty", Toast.LENGTH_SHORT).show();
        }

        
        modelSetNameView = findViewById(R.id.modelSetNameView);
        modelSetNameView.setText(modelSetName);
        Button nextButton = findViewById(R.id.nextButton);

        selectedAnswers = new ArrayList<>(20);
        for (int i = 0; i < fullQuestionList.size(); i++) {
            selectedAnswers.add(null); // setting every selectedAnswer to null
        }
       if(fullQuestionList.size()==0){
           Toast.makeText(this, "FullQuestion list is empty ", Toast.LENGTH_SHORT).show();
       }
        loadQuestions();
        nextButton.setOnClickListener(v -> {
            calculateScore();
            if (currentIndex + 5 < fullQuestionList.size()) {
                currentIndex += 5;
                loadQuestions();
            } else {
                nextButton.setEnabled(false);
                showFinalScore();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loadQuestions() {
        List<ModelSetQuestions> subList = fullQuestionList.subList(currentIndex, Math.min(currentIndex + 5, fullQuestionList.size()));
        questionAdapter = new ModelTestQuestionAdapter(this, subList, selectedAnswers);
        if (listView == null) {
            Toast.makeText(this, "ListView is null", Toast.LENGTH_SHORT).show();
        } else {
            listView.setAdapter(questionAdapter);
        }
    }

    private void calculateScore() {
        for (int i = currentIndex; i < Math.min(currentIndex + 5, fullQuestionList.size()); i++) {
            ModelSetQuestions question = fullQuestionList.get(i);
            String selectedAnswer = selectedAnswers.get(i);

            if (selectedAnswer != null ) {
                if(selectedAnswer.equals(question.getCorrectAnswer())){
                    score++; // Increment score if the answer is correct
                }else{
                    switch (courseName){
                        case "IOE":
                        score-=0.1F; //10% negative marking
                        break;
                        case "LOKSEWA":
                            score-= 0.2F; //20% negative Marking
                            break;
                        default:
                            //no negative marking for +2 and CEE exam
                    }

                }
            }
        }
    }

    private void showFinalScore() {
        // Show the score to the user, e.g., in a Toast or a new activity/dialog
        Intent intent = new Intent(this, QuizFinished.class);
        intent.putExtra("modelSetName",modelSetName);
        String stringScore= String.format("%.1f",score);
        String finalFeedback = "You have Completed The Model Set Test with Score:"+stringScore;
                intent.putExtra("finalFeedback",finalFeedback);
        startActivity(intent);
//        Toast.makeText(this, "Your score is: " + stringScore, Toast.LENGTH_LONG).show();
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    private void updateCountDownTimer() {
        int minutes = (int)(mTimeMilliLeft/1000)/60;
        int seconds = (int)(mTimeMilliLeft/1000)%60;
        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        timer.setText(timeLeftFormatted);
    }
}
