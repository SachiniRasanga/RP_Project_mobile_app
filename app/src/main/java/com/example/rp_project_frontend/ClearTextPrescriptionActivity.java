package com.example.rp_project_frontend;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.List;

public class ClearTextPrescriptionActivity extends AppCompatActivity {

    CardView clearTextDisplaySpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_text_prescription);

        clearTextDisplaySpace = findViewById(R.id.clear_text_display_space);

        Intent uploadImageIntent = getIntent();
        ArrayList<String> predicted_class = uploadImageIntent.getStringArrayListExtra("predicted_class");
        clearTextDisplaySpace.setActivated(true);
        clearTextDisplaySpace.setTooltipText(predicted_class.get(0));
    }
}