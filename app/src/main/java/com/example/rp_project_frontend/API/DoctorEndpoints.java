package com.example.rp_project_frontend.API;

import com.example.rp_project_frontend.Model.Doctor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DoctorEndpoints {

    @GET("/doctor/list")
    Call<List<Doctor>> getDoctorList(
            @Query("docType") String docType,
            @Query("search") String search
    );

    @GET("/doctor/{id}")
    Call<Doctor> getDoctorById(@Path("id") String id);

}
