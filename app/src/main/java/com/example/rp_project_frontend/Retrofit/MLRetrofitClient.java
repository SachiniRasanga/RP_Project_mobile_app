package com.example.rp_project_frontend.Retrofit;

import com.example.rp_project_frontend.API.DiseaseIdentifyEndpoints;
import com.example.rp_project_frontend.API.TextIdentifyEndpoints;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MLRetrofitClient {
    private static String BASE_URL = "http://192.168.1.100:8000";
    private static MLRetrofitClient retrofitClient;
    private static Retrofit retrofit;

    private MLRetrofitClient() {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(5, 10, TimeUnit.MINUTES)) // Adjust pool settings as needed
                .callTimeout(100,TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
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
