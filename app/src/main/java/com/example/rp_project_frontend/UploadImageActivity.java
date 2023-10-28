package com.example.rp_project_frontend;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.rp_project_frontend.Model.TextIdentifyResponseDto;
import com.example.rp_project_frontend.Retrofit.MLRetrofitClient;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadImageActivity extends AppCompatActivity {

    ImageView uploadImageView;
    AppCompatButton confirmBtn;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Logger logger = Logger.getLogger("Upload Image Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadimage);

        uploadImageView = findViewById(R.id.presImageView);
        confirmBtn = findViewById(R.id.upload_image_confirm_button);

        Intent intent = getIntent();
        Uri image = Uri.parse(intent.getStringExtra("image"));
        uploadImageView.setImageURI(image);
        logger.info("Start Base64 convert");
        String base64Image = null;
        try {
            InputStream inputStream = getContentResolver().openInputStream(image);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            base64Image = Base64.encodeToString(byteArray, Base64.DEFAULT);

            // Now you have the image in base64Image
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("End Base64 convert");

        String finalBase64Image = base64Image;
        confirmBtn.setOnClickListener(view -> {
            logger.info("API call");
            logger.info("Base64String : " + finalBase64Image);

            Call<TextIdentifyResponseDto> call = MLRetrofitClient.getInstance()
                    .getTextIdentifyEndpoint()
                    .predictPrescriptionText(finalBase64Image);

            call.enqueue(new Callback<TextIdentifyResponseDto>() {
                @Override
                public void onResponse(Call<TextIdentifyResponseDto> call, Response<TextIdentifyResponseDto> response) {
                    if (response.isSuccessful()) {
                        logger.info("Success");
                        Toast.makeText(UploadImageActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        logger.info(response.message());

                        TextIdentifyResponseDto responseDto = response.body();
                        logger.info("Response : " + responseDto.getPredicted_class());

                        Intent clearTextIntent = new Intent(UploadImageActivity.this, ClearTextPrescriptionActivity.class);
                        clearTextIntent.putStringArrayListExtra(
                                "predicted_class",
                                (ArrayList<String>) responseDto.getPredicted_class());
                        startActivity(clearTextIntent);
                    } else {
                        logger.warning(response.message());
                        Toast.makeText(UploadImageActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<TextIdentifyResponseDto> call, Throwable t) {
                    Toast.makeText(UploadImageActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });
        });
    }

}
