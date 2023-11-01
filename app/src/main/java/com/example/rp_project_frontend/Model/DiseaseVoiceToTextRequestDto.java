package com.example.rp_project_frontend.Model;

public class DiseaseVoiceToTextRequestDto {
    private String voice_input;

    public DiseaseVoiceToTextRequestDto(String voice_input) {
        this.voice_input = voice_input;
    }

    public String getVoice_input() {
        return voice_input;
    }

    public void setVoice_input(String voice_input) {
        this.voice_input = voice_input;
    }
}
