package com.example.practice.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ApiResponse {

    private String userId;

    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdDate;

    public String getUserId() {return userId;}

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedDate() {return createdDate;}

    public void setCreatedDate(LocalDateTime createdDate) {this.createdDate = createdDate;}

}

