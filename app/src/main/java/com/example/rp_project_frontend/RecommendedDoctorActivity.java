package com.example.rp_project_frontend;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
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
    TextView searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_doctor);

        doctorNameTextView = findViewById(R.id.doctor_name_view);
        doctorViewBtn = findViewById(R.id.doctor_view_eye_btn);
        doctorImageView = findViewById(R.id.doctor_image_view);
        doctorListView = findViewById(R.id.doctor_list_view);
        searchView = findViewById(R.id.searchView);
        Spinner doctorType = findViewById(R.id.doctor_spinner);

        doctorListAdapter = new DoctorListAdapter(this, new ArrayList<Doctor>());
        doctorListView.setAdapter(doctorListAdapter);

        List<Doctor> doctorList = new ArrayList<>();

//        String search = searchView.getText().toString();
//        Integer docType = doctorType.getSelectedItemPosition();

        Call<List<Doctor>> call = RetrofitClient.getInstance().getDoctorEndpoint().getDoctorList("", "");
        call.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                if (response.isSuccessful()) {
                    doctorList.addAll(response.body());
                    doctorListAdapter.notifyDataSetChanged();
                    doctorListAdapter.addAll(doctorList);
                } else {
                    Toast.makeText(RecommendedDoctorActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {
                Toast.makeText(RecommendedDoctorActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}