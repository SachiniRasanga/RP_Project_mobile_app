package com.example.rp_project_frontend;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rp_project_frontend.Model.UserCreateRequest;
import com.example.rp_project_frontend.Model.UserCreateResponse;
import com.example.rp_project_frontend.Retrofit.RetrofitClient;

import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText firstNameEditText, lastNameEditText, userNameEditText, emailEditText, mobileNoEditText, passwordEditText, userTypeEditText;
    Button registerBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        userNameEditText = findViewById(R.id.userName);
        emailEditText = findViewById(R.id.email);
        mobileNoEditText = findViewById(R.id.mobileNo);
        passwordEditText = findViewById(R.id.password);
        userTypeEditText = findViewById(R.id.userType);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(view -> registerUser());
    }

    private void registerUser() {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String username = userNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String mobileNo = mobileNoEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String userType = userTypeEditText.getText().toString();

        if (username.isEmpty()) {
            userNameEditText.requestFocus();
            userNameEditText.setError("username cannot be empty");
        }
        if (firstName.isEmpty()) {
            firstNameEditText.requestFocus();
            firstNameEditText.setError("Firstname cannot be empty");
        }
        if (lastName.isEmpty()) {
            lastNameEditText.requestFocus();
            lastNameEditText.setError("Lastname cannot be empty");
        }
        if (mobileNo.isEmpty()) {
            mobileNoEditText.requestFocus();
            mobileNoEditText.setError("Mobile No cannot be empty");
        }
        if (email.isEmpty()) {
            emailEditText.requestFocus();
            emailEditText.setError("Email cannot be empty");
        }

        String fullName = firstName + " " + lastName;
        UserCreateRequest userCreateRequest = new UserCreateRequest(
            fullName, username,email,password,mobileNo,userType
        );

        Call<UserCreateResponse> call = RetrofitClient
                .getInstance()
                .getEndpoint()
                .createUser(userCreateRequest);

        call.enqueue(new Callback<UserCreateResponse>() {
            @Override
            public void onResponse(Call<UserCreateResponse> call, Response<UserCreateResponse> response) {
                UserCreateResponse userCreateResponse = response.body();
                Logger logger = Logger.getLogger("Register Activity");
                logger.info(userCreateResponse.getMessage());
                if (response.isSuccessful()) {
                    logger.info("Register successful");
                    logger.info(userCreateResponse.getMessage());
                    Toast.makeText(RegisterActivity.this, userCreateResponse.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegisterActivity.this, userCreateResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserCreateResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
