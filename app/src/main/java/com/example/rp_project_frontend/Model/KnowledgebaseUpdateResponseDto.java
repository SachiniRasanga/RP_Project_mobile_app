package com.example.rp_project_frontend.Model;

public class KnowledgebaseUpdateResponseDto {
    public String status;
    private String response;

    public KnowledgebaseUpdateResponseDto(String status, String response) {
        this.status = status;
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
