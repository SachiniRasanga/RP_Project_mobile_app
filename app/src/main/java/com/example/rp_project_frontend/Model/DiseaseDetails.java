package com.example.rp_project_frontend.Model;

public class DiseaseDetails {
    private String message;
    private String doctor;
    private String disease;

    public DiseaseDetails(String message, String doctor, String disease, String status) {
        this.message = message;
        this.doctor = doctor;
        this.disease = disease;
    }

    public String getMessage() {
        return message;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getDisease() {
        return disease;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
