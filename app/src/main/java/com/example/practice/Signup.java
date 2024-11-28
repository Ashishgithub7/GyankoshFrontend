package com.example.practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practice.Api.ApiClient;
import com.example.practice.Api.ApiService;
import com.example.practice.Entity.ApiResponse;
import com.example.practice.Entity.SignupRequest;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {

    TextInputEditText usernameEditText,passwordEditText,confirmPasswordEditText,emailEditText;
    MaterialButton signupBtn;
    ApiClient apiClient;
//    SignupRequest signupRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        emailEditText = findViewById(R.id.emailEditText);
        signupBtn = findViewById(R.id.signup);



        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameEditText.setError(null);
                passwordEditText.setError(null);
                confirmPasswordEditText.setError(null);
                emailEditText.setError(null);

                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();

                TextInputLayout usernameLayout = findViewById(R.id.username);
                TextInputLayout passwordLayout = findViewById(R.id.password);
                TextInputLayout confirmPasswordLayout = findViewById(R.id.confirmPassword);
                TextInputLayout emailLayout = findViewById(R.id.email);





                boolean hasError = false;

                if (username.isEmpty()) {
//                    TextInputLayout usernameLayout = findViewById(R.id.username);
                    usernameLayout.setError("Required*");
//                    usernameEditText.setError("Required*");
                    hasError = true;
                }else{usernameLayout.setError(null);}

                if (password.isEmpty()) {

                    passwordLayout.setError("Required*");
                    passwordLayout.setEndIconMode(TextInputLayout.END_ICON_NONE);
                    hasError = true;
                }else  passwordLayout.setError(null);

                if( confirmPassword.isEmpty()){

                    confirmPasswordLayout.setError("Required*");
                    confirmPasswordLayout.setEndIconMode(TextInputLayout.END_ICON_NONE);;
                    hasError = true;
                }else confirmPasswordLayout.setError(null);

                if( email.isEmpty()){

                    emailLayout.setError("Required*");
//                    emailEditText.setError("Email cannot be empty");
                    hasError = true;
                }else emailLayout.setError(null);



                if(!hasError) {

                    boolean regexError = false;
                    if (!username.matches("^[A-Za-z][A-Za-z0-9!@#$_]{5,}$")) {
                        usernameLayout.setError("Invalid Username");
                        regexError = true;
                    }
                    if (!password.matches("^[A-Za-z][A-Za-z0-9!@#$_]{5,}$")) {
                        passwordLayout.setError("Invalid Password");
                        regexError = true;
                    }
                    if (!confirmPassword.matches("^[A-Za-z][A-Za-z0-9!@#$_]{5,}$")) {
                        confirmPasswordLayout.setError("Invalid Password");
                        regexError = true;
                    }
                    if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                        emailLayout.setError("Invalid Email");
                        regexError = true;
                    }

                    if (!regexError) {
                        if (!confirmPassword.equals(password)) {
                            confirmPasswordLayout.setError("Confirm Password does not match Password");
                        }else{
                            SignupRequest signupRequest = new SignupRequest(username,password,email);


                            ApiService apiService = ApiClient.getClient().create(ApiService.class);
                            Call<ApiResponse> call = apiService.signup(signupRequest);

                            call.enqueue(new Callback<ApiResponse>(){
                                @Override
                                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                                    if (response.isSuccessful() && response.body() != null) {
                                        Intent intent = new Intent(Signup.this, Login.class);
                                        intent.putExtra("message", "User Registered Successfully");
                                        startActivity(intent);

                                    } else {
                                        Toast.makeText(Signup.this, "Signup failed", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ApiResponse> call, Throwable t) {
                                    Snackbar.make(findViewById(R.id.signup), "error: "+ t.getMessage(), Snackbar.LENGTH_SHORT).show();
//                                Toast.makeText(Signup.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }


                    }

                }
            }
        });
    }
}



