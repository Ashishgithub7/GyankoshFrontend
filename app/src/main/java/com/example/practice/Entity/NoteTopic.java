package com.example.practice.Entity;

import java.util.List;

public class NoteTopic {
    private String topicName;
    private List<String> subtopics;

    public NoteTopic(String topicName, List<String> subtopics) {
        this.topicName = topicName;
        this.subtopics = subtopics;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setSubtopics(List<String> subtopics) {
        this.subtopics = subtopics;
    }

    public String getTopicName() {
        return topicName;
    }

    public List<String> getSubtopics() {
        return subtopics;
    }
}
