package com.example.rp_project_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ClearTextPrescriptionActivity extends AppCompatActivity {

    TextView clearTextDisplaySpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_text_prescription);

        clearTextDisplaySpace = findViewById(R.id.clear_text_view);

        Intent uploadImageIntent = getIntent();
        ArrayList<String> predicted_class = uploadImageIntent.getStringArrayListExtra("predicted_class");

        String identifiedText = "";
        for (String s : predicted_class) {
            identifiedText = identifiedText + "\n" + s;
        }

        clearTextDisplaySpace.setText(identifiedText);
    }
}