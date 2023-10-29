package com.example.rp_project_frontend.Model;

public class DiseaseVoiceToTextResponseDto {
    private String response;
    private String status;

    public DiseaseVoiceToTextResponseDto(String response, String status) {
        this.response = response;
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
