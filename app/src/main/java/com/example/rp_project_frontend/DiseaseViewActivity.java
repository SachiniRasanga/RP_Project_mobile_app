package com.example.rp_project_frontend;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DiseaseViewActivity extends AppCompatActivity {

    TextView diseaseName, doctorType, message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_view);

        diseaseName = findViewById(R.id.disease_name_text);
        doctorType = findViewById(R.id.doctor_type_text);
        message = findViewById(R.id.message);

        String disease = getIntent().getStringExtra("disease");
        String doctor = getIntent().getStringExtra("doctor");
        String msg = getIntent().getStringExtra("message");

        diseaseName.setText(disease);
        doctorType.setText(doctor);
        message.setText(msg);
    }
}