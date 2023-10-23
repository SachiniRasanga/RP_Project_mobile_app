package com.example.rp_project_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DiseaseMainActivity extends AppCompatActivity {

    CardView voiceChatBtn, findDoctorBtn, knowledgeBaseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_main);

        voiceChatBtn = findViewById(R.id.disease_identify_voice_chat_btn);
        findDoctorBtn = findViewById(R.id.disease_identify_find_doctor_btn);
        knowledgeBaseBtn = findViewById(R.id.disease_identify_knowledge_base_btn);

        voiceChatBtn.setOnClickListener(view -> {
            Intent voiceChatIntent = new Intent(DiseaseMainActivity.this, VoiceChatsActivity.class);
            startActivity(voiceChatIntent);
        });

        findDoctorBtn.setOnClickListener(view -> {
            Intent findDoctorIntent = new Intent(DiseaseMainActivity.this, RecommendedDoctorDetailsActivity.class);
            startActivity(findDoctorIntent);
        });

        knowledgeBaseBtn.setOnClickListener(view -> {
            Intent knowledgeBaseIntent = new Intent(DiseaseMainActivity.this, KnowledgebaseActivity.class);
            startActivity(knowledgeBaseIntent);
        });
    }
}