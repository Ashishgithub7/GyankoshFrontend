package com.example.practice.Model_Test;

import java.util.ArrayList;
import java.util.List;

public class Model_test_model {
    private long course_id;
    private String examType;
    private String setName;
    private List<ModelSetQuestions> modelSetQuestionsList;

    public Model_test_model(long course_id, String examType, String setName, List<ModelSetQuestions> modelSetQuestionsList) {
        this.course_id = course_id;
        this.examType = examType;
        this.setName = setName;
        this.modelSetQuestionsList = modelSetQuestionsList;
    }

    public long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(long course_id) {
        this.course_id = course_id;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public List<ModelSetQuestions> getModelSetQuestionsList() {
        return modelSetQuestionsList;
    }

    public void setModelSetQuestionsList(List<ModelSetQuestions> modelSetQuestionsList) {
        this.modelSetQuestionsList = modelSetQuestionsList;
    }
}
