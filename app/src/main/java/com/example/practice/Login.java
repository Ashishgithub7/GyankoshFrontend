package com.example.practice;

import static android.app.PendingIntent.getActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practice.Api.ApiClient;
import com.example.practice.Api.ApiService;
import com.example.practice.Entity.LoginRequest;
import com.example.practice.Entity.LoginResponse;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    TextInputEditText usernameEditText,passwordEditText;
    Button loginBtn;
    private String messages,token;
    private Long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_login);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        final String[] message = {getIntent().getStringExtra("message")};

        // Show the Toast with the retrieved message
        if (message[0] != null) {
            Snackbar.make(findViewById(R.id.main), message[0], Snackbar.LENGTH_SHORT).show();
            Toast.makeText(Login.this, message[0], Toast.LENGTH_SHORT).show();
        }
usernameEditText = findViewById(R.id.usernameEditText);
passwordEditText = findViewById(R.id.passwordEditText);
loginBtn = findViewById(R.id.loginSubmitBtn);

loginBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (!username.isEmpty()) {
            if(!password.isEmpty()){
                TextInputLayout passwordLayout = findViewById(R.id.password);
                passwordLayout.setError(null);
                TextInputLayout usernameLayout = findViewById(R.id.username);
                usernameLayout.setError(null);
                if(username.equals("admin777")&&password.equals("admin777")){
                        Intent intent = new Intent(Login.this,com.example.practice.AdminMain.class);
                        startActivity(intent);
                }else {
//                    HERE USERNAME IS EMAIL IN ACTUAL
                    if(username.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,40}$")){
                        if(password.matches("^[A-Za-z][A-Za-z0-9!@#$_]{7,}$")){

                            LoginRequest loginRequest = new LoginRequest(username,password);

                            ApiService apiService = ApiClient.getClient().create(ApiService.class);
                            Call<LoginResponse> call = apiService.login(loginRequest);

                            call.enqueue(new Callback<LoginResponse>() {
                                @Override
                                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                    if(response.isSuccessful()&&response.body()!=null){
                                        LoginResponse loginResponse = response.body();
                                        token = loginResponse.getToken();
                                        userId = loginResponse.getUserId();
                                        messages = loginResponse.getMessage();
                                        Toast.makeText(Login.this, messages, Toast.LENGTH_SHORT).show();

//                                        CHECK IF ALREADY LOGGED IN
                                        SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
                                        SharedPreferences.Editor editor = pref.edit();
                                        editor.putBoolean("flag",true);
                                        editor.putString("email",username);
                                        editor.putLong("userId",userId);
                                        editor.apply();

                                        Intent intent = new Intent(Login.this, navigation.class);
//                                        intent.putExtra("email",username);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<LoginResponse> call, Throwable t) {
                                    Toast.makeText(Login.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }else{
                            Snackbar.make(findViewById(R.id.main), "Invalid Password(Minimum length: 8)", Snackbar.LENGTH_SHORT).show();
                        }

                    }else{
                        Snackbar.make(findViewById(R.id.main), "Invalid Username(Minimum length: 5)", Snackbar.LENGTH_SHORT).show();
                    }

                }
            }else{
                TextInputLayout passwordLayout = findViewById(R.id.password);
                passwordLayout.setError("Password cannot be empty");
            }
        } else {
            TextInputLayout usernameLayout = findViewById(R.id.username);
            usernameLayout.setError("Username cannot be empty");
        }
    }
});

    }
}