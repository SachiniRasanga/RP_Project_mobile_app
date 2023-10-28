package com.example.rp_project_frontend.Model;

public class VoiceResponseDto {
    private String response;
    private String status;
    private String voice;

    public VoiceResponseDto(String response, String status, String voice) {
        this.response = response;
        this.status = status;
        this.voice = voice;
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

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }
}
