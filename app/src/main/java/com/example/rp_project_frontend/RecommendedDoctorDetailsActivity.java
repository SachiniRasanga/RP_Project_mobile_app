package com.example.rp_project_frontend;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rp_project_frontend.Model.Doctor;
import com.example.rp_project_frontend.Retrofit.RetrofitClient;

import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendedDoctorDetailsActivity extends AppCompatActivity {

    TextView doctorNameTextView, doctorSpecialityTextView, doctorAddressTextView, doctorPhoneTextView, doctorEmailTextView, doctorDescriptionTextView;

    Logger logger = Logger.getLogger("RecommendedDoctorDetailsActivity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_doctor_details);

        doctorNameTextView = findViewById(R.id.doctor_fullname_text);
        doctorSpecialityTextView = findViewById(R.id.doctor_type_text);
        doctorAddressTextView = findViewById(R.id.doctor_address_text);
        doctorPhoneTextView = findViewById(R.id.doctor_mobilenumber_text);
        doctorEmailTextView = findViewById(R.id.doctor_email_text);
        doctorDescriptionTextView = findViewById(R.id.doctor_description_text);

        String id = getIntent().getStringExtra("doctorId");
        logger.info("Doctor ID: " + id);

        Call<Doctor> call = RetrofitClient.getInstance().getDoctorEndpoint().getDoctorById(id);

        call.enqueue(new Callback<Doctor>() {
            @Override
            public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                if (response.isSuccessful()) {
                    Doctor doctor = response.body();

                    doctorNameTextView.setText(doctor.getFullname());
                    doctorSpecialityTextView.setText(doctor.getSpecialization());
                    doctorAddressTextView.setText(doctor.getAddress());
                    doctorPhoneTextView.setText(doctor.getMobileno());
                    doctorEmailTextView.setText(doctor.getEmail());
                    doctorDescriptionTextView.setText(doctor.getDescription());
                } else {
                    Toast.makeText(RecommendedDoctorDetailsActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Doctor> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}