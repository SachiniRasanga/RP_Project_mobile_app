package com.example.rp_project_frontend.Model;

public class VoiceRequestDto {
    private String text;

    public VoiceRequestDto(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
