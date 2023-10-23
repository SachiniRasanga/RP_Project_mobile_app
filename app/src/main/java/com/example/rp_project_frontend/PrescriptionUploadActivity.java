package com.example.rp_project_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PrescriptionUploadActivity extends AppCompatActivity {

    ImageView uploadImageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_upload);

        uploadImageBtn = findViewById(R.id.uploadImageBtn);

        uploadImageBtn.setOnClickListener(view -> {
          Intent intent = new Intent(PrescriptionUploadActivity.this, PopupActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.bottom_to_top, R.anim.no_animation);
        });
    }

}
