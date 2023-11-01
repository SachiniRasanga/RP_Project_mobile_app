package com.example.rp_project_frontend.Model;

public class DiseaseIdentifyResponseDto {
    private String status;
    private DiseaseDetails response;

    public DiseaseIdentifyResponseDto(String status, DiseaseDetails response) {
        this.status = status;
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DiseaseDetails getResponse() {
        return response;
    }

    public void setResponse(DiseaseDetails response) {
        this.response = response;
    }
}