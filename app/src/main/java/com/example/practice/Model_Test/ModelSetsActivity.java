package com.example.practice.Model_Test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practice.Api.ApiClient;
import com.example.practice.Api.ApiService;
import com.example.practice.Entity.McqQuestionResponse;
import com.example.practice.MCQ.McqFirstPage;
import com.example.practice.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelSetsActivity extends AppCompatActivity {
    List<Model_test_model> models;
    private String courseName;
    private String setName = "Set 1";
    private List<ModelSetQuestions> questionList = new ArrayList<>();
    private Model_test_model modelSetResponse;
    private List<ModelSetQuestions> modelSetQuestionsListResponse;
    private ApiService apiService;
    private TextView errorMessage;
    private ModelSetQuestions modelSetQuestions;
    private List<ModelSetQuestions> questions= new ArrayList<>();

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_model_sets);

        courseName = getIntent().getStringExtra("COURSE_NAME");
//        errorMessage = findViewById(R.id.errorMessage);
        setData();

        listView = findViewById(R.id.modelSetList);
        // Create the adapter

        // setting onclick listener on list of model sets
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // Get the selected ModelSetQuestion object
            Model_test_model selectedModelSet = models.get(position);

            // Get the model set name from the selected item
            String modelSetName = selectedModelSet.getSetName(); // Adjust this depending on how you store the model set name

            // Create an Intent to navigate to the ModelTest activity
            Intent intent = new Intent(ModelSetsActivity.this, ModelTestActivity.class);
            intent.putExtra("modelSetName", modelSetName);
            intent.putExtra("COURSE_NAME",courseName);
            intent.putParcelableArrayListExtra("questionList", (ArrayList<? extends Parcelable>) questions);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    private void setData() {
        apiService = ApiClient.getClient().create(ApiService.class);

        Call<List<ModelSetQuestions>> call = apiService.startModelTest(courseName, "Set 1");
        call.enqueue(new Callback<List<ModelSetQuestions>>() {
            @Override
            public void onResponse(Call<List<ModelSetQuestions>> call, Response<List<ModelSetQuestions>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    questions = response.body();

                    models = new ArrayList<>();
                    models.add(new Model_test_model(1, "IOE", "Model Set 1", questions));



                    ModelTestAdapter adapter = new ModelTestAdapter(ModelSetsActivity.this, models);

                    // Find the ListView and set the adapter
                    listView.setAdapter(adapter);


                    //                    modelSetQuestionsListResponse = response.body();
                    // Now you have the list of questions, update the UI or store this data for later use
                    Toast.makeText(ModelSetsActivity.this, "Questions fetched successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    errorMessage.setText("Failed to fetch questions");
                    Toast.makeText(ModelSetsActivity.this, "Failed to fetch questions", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<List<ModelSetQuestions>> call, Throwable t) {
                errorMessage.setText("An error occurred: " + t.getMessage());
                Toast.makeText(ModelSetsActivity.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    // Sample data for models


    }
}
