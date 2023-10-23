package com.example.rp_project_frontend;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DiseaseMainActivity extends AppCompatActivity {

    CardView voiceChatBtn, findDoctorBtn, knowledgeBaseBtn;
    Spinner doctorSpinner;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_main);

        voiceChatBtn = findViewById(R.id.disease_identify_voice_chat_btn);
        findDoctorBtn = findViewById(R.id.disease_identify_find_doctor_btn);
        knowledgeBaseBtn = findViewById(R.id.disease_identify_knowledge_base_btn);

        //Dropdown
//        doctorSpinner = findViewById(R.id.doctor_spinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dropdown_items, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        doctorSpinner.setAdapter(adapter);
//
//        doctorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        voiceChatBtn.setOnClickListener(view -> {
            Intent voiceChatIntent = new Intent(DiseaseMainActivity.this, VoiceChatsActivity.class);
            startActivity(voiceChatIntent);
        });

        findDoctorBtn.setOnClickListener(view -> {
            Intent findDoctorIntent = new Intent(DiseaseMainActivity.this, RecommendedDoctorActivity.class);
            startActivity(findDoctorIntent);
        });

        knowledgeBaseBtn.setOnClickListener(view -> {
            Intent knowledgeBaseIntent = new Intent(DiseaseMainActivity.this, KnowledgebaseActivity.class);
            startActivity(knowledgeBaseIntent);
        });
    }
}