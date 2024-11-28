package com.example.practice.Entity;

public class SignupRequest {
    private String username;
    private String password;
    private String email;
//    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getConfirmPassword() {
//        return confirmPassword;
//    }
//
//    public void setConfirmPassword(String confirmPassword) {
//        this.confirmPassword = confirmPassword;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public SignupRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
//        this.confirmPassword = confirmPassword;
    }
}
