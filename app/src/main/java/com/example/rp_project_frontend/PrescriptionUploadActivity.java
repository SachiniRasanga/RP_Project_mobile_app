package com.example.rp_project_frontend;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
