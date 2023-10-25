package com.example.rp_project_frontend;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

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

        uploadImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_popup);

        View GalleryBtn = dialog.findViewById(R.id.chooseFromGalleryBtn);
        View cancelBtn = dialog.findViewById(R.id.cancelBtn);

        GalleryBtn.setOnClickListener(view -> {

            openGallery();
        });


        cancelBtn.setOnClickListener(view -> {
            Intent intent = new Intent(PrescriptionUploadActivity.this, PrescriptionUploadActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.no_animation, R.anim.bottom_to_top);
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations =R.layout.activity_popup;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }


    private void openGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        openGallery.launch(intent);
    }
    ActivityResultLauncher<Intent> openGallery = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                Uri uriImage = result.getData().getData();
                Intent intent = new Intent(PrescriptionUploadActivity.this, UploadImageActivity.class);
                intent.putExtra("image", uriImage.toString());
                startActivity(intent);
            });

}
