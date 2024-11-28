package com.example.practice.Entity;

import java.io.Serializable;

public class McqQuestionResponse implements Serializable {
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String feedback;
    private String correctAnswer;
    private String finalFeedback;
    private boolean quizComplete;

    public boolean isQuizComplete() {
        return quizComplete;
    }

    public void setQuizComplete(boolean quizComplete) {
        this.quizComplete = quizComplete;
    }

    public String getFinalFeedback() {
        return finalFeedback;
    }

    public void setFinalFeedback(String finalFeedback) {
        this.finalFeedback = finalFeedback;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public McqQuestionResponse(String questionText, String optionA, String optionB, String optionC, String optionD, String feedback, String correctAnswer, String finalFeedback, boolean quizComplete) {
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.feedback = feedback;
        this.correctAnswer = correctAnswer;
        this.finalFeedback = finalFeedback;
        this.quizComplete = quizComplete;
    }


}
