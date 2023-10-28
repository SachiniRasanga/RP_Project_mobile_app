package com.example.rp_project_frontend;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UploadImageActivity extends AppCompatActivity {

    ImageView uploadImageView;

     Button rotateButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadimage);

        uploadImageView = findViewById(R.id.presImageView);
        rotateButton = findViewById(R.id.rotatebutton);

        Intent intent = getIntent();
        Uri image = Uri.parse(intent.getStringExtra("image"));

        uploadImageView.setImageURI(image);

        rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateImage();
            }
        });
    }

    private void rotateImage() {

        float currentRotation = uploadImageView.getRotation();
        currentRotation += 90;
        uploadImageView.setRotation(currentRotation);
    }
}
