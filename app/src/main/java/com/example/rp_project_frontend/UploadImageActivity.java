package com.example.rp_project_frontend;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UploadImageActivity extends AppCompatActivity {

    ImageView uploadImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadimage);

        uploadImageView = findViewById(R.id.presImageView);

        Intent intent = getIntent();
        Uri image = Uri.parse(intent.getStringExtra("image"));

        uploadImageView.setImageURI(image);
    }
}
