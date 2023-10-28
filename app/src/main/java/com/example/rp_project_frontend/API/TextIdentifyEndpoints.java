package com.example.rp_project_frontend.API;

import com.example.rp_project_frontend.Model.DownloadImageRequestDto;
import com.example.rp_project_frontend.Model.DownloadImageResponseDto;
import com.example.rp_project_frontend.Model.TextIdentifyResponseDto;
import com.example.rp_project_frontend.Model.VoiceRequestDto;
import com.example.rp_project_frontend.Model.VoiceResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TextIdentifyEndpoints {

    @FormUrlEncoded
    @POST("/text-identify/predict")
    Call<TextIdentifyResponseDto> predictPrescriptionText(@Field("file") String base64Image);

    @POST("/text-identify/text-to-image")
    Call<DownloadImageResponseDto> textToImage(@Body DownloadImageRequestDto downloadImageRequestDto);

    @POST("/text-identify/text-to-speech")
    Call<VoiceResponseDto> textToSpeech(@Body VoiceRequestDto voiceRequestDto);


}