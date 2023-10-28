package com.example.rp_project_frontend.Model;

public class DownloadImageResponseDto {
    private String response;
    private String status;
    private String image;

    public DownloadImageResponseDto(String response, String status, String image) {
        this.response = response;
        this.status = status;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
