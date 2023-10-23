package com.example.rp_project_frontend;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rp_project_frontend.Model.Doctor;
import com.example.rp_project_frontend.Retrofit.RetrofitClient;
import com.example.rp_project_frontend.adapter.DoctorListAdapter;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendedDoctorActivity extends AppCompatActivity {
    TextView doctorNameTextView;
    ImageButton doctorViewBtn;
    ImageView doctorImageView;
    ListView doctorListView;
    DoctorListAdapter doctorListAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_doctor);

        doctorNameTextView = findViewById(R.id.doctor_name_view);
        doctorViewBtn = findViewById(R.id.doctor_view_eye_btn);
        doctorImageView = findViewById(R.id.doctor_image_view);

        doctorListView = findViewById(R.id.doctor_list_view);
        doctorListAdapter = new DoctorListAdapter(this, new ArrayList<Doctor>());

        doctorListView.setAdapter(doctorListAdapter);

        List<Doctor> doctorList = new ArrayList<>();
        Doctor doctor1 = new Doctor("Doctor 1", "Ayurwedic", "testmail", "0717585960", "test", "test", "test");
        Doctor doctor2 = new Doctor("Doctor 2", "Ayurwedic", "testmail", "0717585960", "test", "test", "test");
        Doctor doctor3 = new Doctor("Doctor 3", "Ayurwedic", "testmail", "0717585960", "test", "test", "test");

        doctorList.add(doctor1);
        doctorList.add(doctor2);
        doctorList.add(doctor3);

        doctorListAdapter.addAll(doctorList);

    }


}