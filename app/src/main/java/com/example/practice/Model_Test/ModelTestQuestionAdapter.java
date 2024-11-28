package com.example.practice.Model_Test;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.practice.R;

import java.util.ArrayList;
import java.util.List;

public class ModelTestQuestionAdapter extends android.widget.BaseAdapter {
    private Context context;
    private List<ModelSetQuestions> questions;
    private LayoutInflater inflater;
    private ArrayList<String> selectedAnswers;

    public ModelTestQuestionAdapter(Context context, List<ModelSetQuestions> questions, ArrayList<String> selectedAnswers) {
        this.context = context;
        this.questions = questions;
        this.selectedAnswers = selectedAnswers;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Object getItem(int position) {
        return questions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.modelset_question_row, parent, false);
        }

        // get views to inflate new row
//        TextView qid = convertView.findViewById(R.id.qid);
        TextView questionView = convertView.findViewById(R.id.questionView);
        Button optionA = convertView.findViewById(R.id.optionA);
        Button optionB = convertView.findViewById(R.id.optionB);
        Button optionC = convertView.findViewById(R.id.optionC);
        Button optionD = convertView.findViewById(R.id.optionD);

        // get currentQuestion from the list
        ModelSetQuestions currentQuestion = questions.get(position);
        int globalPosition = getGlobalPosition(position);

        // setting text on the new row
//        qid.setText(currentQuestion.getQid().toString() + ".");
        questionView.setText(currentQuestion.getQuestion_text());
        optionA.setText(currentQuestion.getOption1());
        optionB.setText(currentQuestion.getOption2());
        optionC.setText(currentQuestion.getOption3());
        optionD.setText(currentQuestion.getOption4());

        // Reset buttons to be clickable and clear previous selections
        optionA.setBackgroundColor(Color.TRANSPARENT);
        optionB.setBackgroundColor(Color.TRANSPARENT);
        optionC.setBackgroundColor(Color.TRANSPARENT);
        optionD.setBackgroundColor(Color.TRANSPARENT);

        optionA.setEnabled(true);
        optionB.setEnabled(true);
        optionC.setEnabled(true);
        optionD.setEnabled(true);

        // Restore the selected answer
        String selectedAnswer = selectedAnswers.get(globalPosition);
        if (selectedAnswer != null) {
            if (selectedAnswer.equals(currentQuestion.getOption1())) {
                optionA.setBackgroundColor(ContextCompat.getColor(context, R.color.secondary));
                disableAllOptions(optionA, optionB, optionC, optionD);
            } else if (selectedAnswer.equals(currentQuestion.getOption2())) {
                optionB.setBackgroundColor(ContextCompat.getColor(context, R.color.secondary));
                disableAllOptions(optionA, optionB, optionC, optionD);
            } else if (selectedAnswer.equals(currentQuestion.getOption3())) {
                optionC.setBackgroundColor(ContextCompat.getColor(context, R.color.secondary));
                disableAllOptions(optionA, optionB, optionC, optionD);
            } else if (selectedAnswer.equals(currentQuestion.getOption4())) {
                optionD.setBackgroundColor(ContextCompat.getColor(context, R.color.secondary));
                disableAllOptions(optionA, optionB, optionC, optionD);
            }
        }

        // Click listeners for the options
        View.OnClickListener optionClickListener = v -> {
            String answer = null;
            if (v.getId() == R.id.optionA) {
                answer = currentQuestion.getOption1();
            } else if (v.getId() == R.id.optionB) {
                answer = currentQuestion.getOption2();
            } else if (v.getId() == R.id.optionC) {
                answer = currentQuestion.getOption3();
            } else if (v.getId() == R.id.optionD) {
                answer = currentQuestion.getOption4();
            }
            if (answer != null) {
                selectedAnswers.set(globalPosition, answer);
                notifyDataSetChanged();
            }
        };

        optionA.setOnClickListener(optionClickListener);
        optionB.setOnClickListener(optionClickListener);
        optionC.setOnClickListener(optionClickListener);
        optionD.setOnClickListener(optionClickListener);

        return convertView;
    }

    private int getGlobalPosition(int position) {
        // Calculate the global position based on the current index
        return position + ((ModelTestActivity) context).getCurrentIndex();
    }

    private void disableAllOptions(Button optionA, Button optionB, Button optionC, Button optionD) {
        optionA.setEnabled(false);
        optionB.setEnabled(false);
        optionC.setEnabled(false);
        optionD.setEnabled(false);
    }
}
