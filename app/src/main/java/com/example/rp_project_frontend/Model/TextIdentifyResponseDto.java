package com.example.rp_project_frontend.Model;

import java.util.List;

public class TextIdentifyResponseDto {
    private List<String> predicted_class;

    public TextIdentifyResponseDto(List<String> predicted_class) {
        this.predicted_class = predicted_class;
    }

    public List<String> getPredicted_class() {
        return predicted_class;
    }

    public void setPredicted_class(List<String> predicted_class) {
        this.predicted_class = predicted_class;
    }
}