package com.example.rp_project_frontend.Model;

public class KnowledgebaseUpdateRequestDto {
    private String disease;
    private String symptom;

    public KnowledgebaseUpdateRequestDto(String disease, String symptom) {
        this.disease = disease;
        this.symptom = symptom;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
}
