package com.example.rp_project_frontend.Model;

public class DiseaseIdentifyRequestDto {
    private String symptoms;

    public DiseaseIdentifyRequestDto(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
}
