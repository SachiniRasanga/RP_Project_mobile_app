package com.example.rp_project_frontend;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rp_project_frontend.Model.UserLoginRequest;
import com.example.rp_project_frontend.Model.UserLoginResponse;
import com.example.rp_project_frontend.Retrofit.RetrofitClient;

import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText userNameEditText, passwordEditText;
    Button loginBtn;
    TextView registerTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameEditText = findViewById(R.id.loginUsername);
        passwordEditText = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginBtn);
        registerTextView = findViewById(R.id.registerBtn);

        loginBtn.setOnClickListener(view -> loginUser());

        registerTextView.setOnClickListener(view -> {
            Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(registerIntent);
        });
    }

    private void loginUser() {

        Logger logger = Logger.getLogger("Login Activity");
        logger.info("Start login user method");

        String username = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.isEmpty()) {
            userNameEditText.requestFocus();
            userNameEditText.setError("username cannot be empty");
        }
        if (password.isEmpty()) {
            passwordEditText.requestFocus();
            passwordEditText.setError("password cannot be empty");
        }
        logger.info("Validations done");

        UserLoginRequest userLoginRequest = new UserLoginRequest(
                username, password
        );

        logger.info("Set username and password to request");

        logger.info("Call to API");
        Call<UserLoginResponse> call = RetrofitClient
                .getInstance()
                .getEndpoint()
                .loginUser(userLoginRequest);

        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                UserLoginResponse userLoginResponse = response.body();
                if (response.isSuccessful()) {

                    if (response.body().getUserid() != null) {
                        Logger logger = Logger.getLogger("Login Activity");
                        logger.info("Login request success");
                        logger.info("UserId  : " + userLoginResponse.getUserid());
                        logger.info("Message : " + userLoginResponse.getMessage());
                        logger.info("User type    : " + userLoginResponse.getUsertype());

                        SharedPreferences sharedPreferences = getSharedPreferences("user_details", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        String userId = userLoginResponse.getUserid();
                        editor.putString("userId", userId);
                        editor.apply();

                        Toast.makeText(LoginActivity.this, userLoginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, userLoginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
