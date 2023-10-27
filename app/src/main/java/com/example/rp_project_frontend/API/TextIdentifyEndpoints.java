package com.example.rp_project_frontend.API;

import com.example.rp_project_frontend.Model.TextIdentifyResponseDto;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TextIdentifyEndpoints {

    @FormUrlEncoded
    @POST("/text-identify/predict")
    Call<TextIdentifyResponseDto> predictPrescriptionText(@Field("file") String base64Image);

}
