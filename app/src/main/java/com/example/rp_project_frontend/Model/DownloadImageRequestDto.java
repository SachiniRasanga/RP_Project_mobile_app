package com.example.rp_project_frontend.Model;

public class DownloadImageRequestDto {
    private String text;

    public DownloadImageRequestDto(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
