package com.example.rp_project_frontend;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PopupActivity extends AppCompatActivity {

    Button chooseFromGalleryBtn, cancelBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        chooseFromGalleryBtn = findViewById(R.id.chooseFromGalleryBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

        cancelBtn.setOnClickListener(view -> {
            Intent intent = new Intent(PopupActivity.this, PrescriptionUploadActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.no_animation, R.anim.bottom_to_top);
        });

        chooseFromGalleryBtn.setOnClickListener(view -> {
            openGallery();
        });
    }

    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        openGallery.launch(intent);
    }

//    ActivityResultLauncher<Intent> openGallery = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(), result -> {
//                Uri uriImage = result.getData().getData();
//                uploadImageView.setImageURI(uriImage);
//            });

    ActivityResultLauncher<Intent> openGallery = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                Uri uriImage = result.getData().getData();
                Intent intent = new Intent(PopupActivity.this, UploadImageActivity.class);
                intent.putExtra("image", uriImage.toString());
                startActivity(intent);
            });
}
