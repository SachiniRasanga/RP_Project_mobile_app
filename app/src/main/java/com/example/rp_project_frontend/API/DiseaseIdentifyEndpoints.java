package com.example.rp_project_frontend.API;

import com.example.rp_project_frontend.Model.DiseaseIdentifyRequestDto;
import com.example.rp_project_frontend.Model.DiseaseIdentifyResponseDto;
import com.example.rp_project_frontend.Model.DiseaseVoiceToTextRequestDto;
import com.example.rp_project_frontend.Model.DiseaseVoiceToTextResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DiseaseIdentifyEndpoints {

    @POST("/disease-identify/predict")
    Call<DiseaseIdentifyResponseDto> predictDisease(@Body DiseaseIdentifyRequestDto diseaseIdentifyRequest);

    @POST("/disease-identify/voice-to-text")
    Call<DiseaseVoiceToTextResponseDto> voiceToText(@Body DiseaseVoiceToTextRequestDto diseaseVoiceToTextRequestDto);

}
