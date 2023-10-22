package com.example.rp_project_frontend;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rp_project_frontend.Model.ApplicationUser;
import com.example.rp_project_frontend.Retrofit.RetrofitClient;

import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    TextView fullname, email, mobileNo, userType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullname = findViewById(R.id.profile_fullname_text);
        email = findViewById(R.id.profile_email_text);
        mobileNo = findViewById(R.id.profile_mobilenumber_text);
        userType = findViewById(R.id.profile_usertype_text);

        SharedPreferences sharedPreferences = getSharedPreferences("user_details", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId", "");

        Logger logger = Logger.getLogger("Profile");
        logger.info("userId : " + userId);

        Call<ApplicationUser> call = RetrofitClient
                .getInstance()
                .getEndpoint()
                .getUserById(userId);

        call.enqueue(new Callback<ApplicationUser>() {
            @Override
            public void onResponse(Call<ApplicationUser> call, Response<ApplicationUser> response) {
                if (response.isSuccessful()) {
                    ApplicationUser applicationUser = response.body();

                    logger.info("Full name : " + applicationUser.getFullname());
                    logger.info("Email     : " + applicationUser.getEmail());

                    fullname.setText(applicationUser.getFullname());
                    email.setText(applicationUser.getEmail());
                    mobileNo.setText(applicationUser.getMobileno());
                    userType.setText(applicationUser.getUsertype());
                } else {
                    Toast.makeText(ProfileActivity.this, "ERROR", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<ApplicationUser> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
