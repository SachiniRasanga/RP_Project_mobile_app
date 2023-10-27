package com.example.rp_project_frontend.Retrofit;

import com.example.rp_project_frontend.API.AuthEndpoints;
import com.example.rp_project_frontend.API.DiseaseIdentifyEndpoints;
import com.example.rp_project_frontend.API.TextIdentifyEndpoints;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MLRetrofitClient {
    private static String BASE_URL = "http://192.168.1.102:8000";
    private static MLRetrofitClient retrofitClient;
    private static Retrofit retrofit;

    private MLRetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized MLRetrofitClient getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new MLRetrofitClient();
        }
        return retrofitClient;
    }

    public TextIdentifyEndpoints getTextIdentifyEndpoint() {
        return retrofit.create(TextIdentifyEndpoints.class);
    }

    public DiseaseIdentifyEndpoints getDiseaseIdentifyEndpoint() {
        return retrofit.create(DiseaseIdentifyEndpoints.class);
    }
}
