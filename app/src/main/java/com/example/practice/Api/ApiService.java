package com.example.practice.Api;

import com.example.practice.Entity.ApiResponse;
import com.example.practice.Entity.LoginRequest;
import com.example.practice.Entity.LoginResponse;
import com.example.practice.Entity.McqQuestionResponse;
import com.example.practice.Entity.SignupRequest;
import com.example.practice.Model_Test.ModelSetQuestions;
import com.example.practice.Model_Test.Model_test_model;
import com.example.practice.Notes.PDFResponse;

import java.util.List;

import Video.VideoResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiService {
    @POST("/api/v1/user/register")
    Call<ApiResponse> signup(@Body SignupRequest signupRequest);

    @POST("/api/v1/user/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/api/v1/quiz/start")
    Call<McqQuestionResponse> startQuiz(@Query("course") String course,@Query("userId") Long userId);

    @POST("/api/v1/quiz/submit")
    Call<McqQuestionResponse> submitAnswer(@Query("answer") String answer,@Query("userId") Long userId);

    @GET("/api/v1/quiz1/start")
    Call<List<ModelSetQuestions>> startModelTest(@Query("course") String examType, @Query("setName") String setName);

    @GET("/api/v1/note/get")
    Call<PDFResponse> getPdfUri(@Query("subTopic") String subtopic);

//    @GET("/api/v1/video/get")
//    Call<VideoResponse> getVideoUrl(@Query("subTopic") String subTopic);

    // This method allows downloading the file directly using a URL
    @GET
    Call<ResponseBody> downloadFileByUrl(@Url String fileUrl);
}
