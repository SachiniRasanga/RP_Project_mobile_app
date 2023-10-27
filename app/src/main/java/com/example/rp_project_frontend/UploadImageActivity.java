package com.example.rp_project_frontend;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rp_project_frontend.Model.TextIdentifyResponseDto;
import com.example.rp_project_frontend.Retrofit.MLRetrofitClient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadImageActivity extends AppCompatActivity {

    ImageView uploadImageView;
    Button confirmBtn;

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
        String base64String = null;
//        try {
//            InputStream inputStream = getContentResolver().openInputStream(image);
//            byte[] buffer = new byte[inputStream.available()];
//            inputStream.read(buffer);
//            inputStream.close();
//            logger.info("Converting base64");
//            base64String = Base64.encodeToString(buffer, Base64.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String finalBase64String = base64String;
        logger.info("Base64String : " + finalBase64String);
        confirmBtn.setOnClickListener(view -> {
//            Call<TextIdentifyResponseDto> call = MLRetrofitClient
//                    .getInstance()
//                    .getTextIdentifyEndpoint()
//                    .predictPrescriptionText(finalBase64String);
//
//            call.enqueue(new Callback<TextIdentifyResponseDto>() {
//                @Override
//                public void onResponse(Call<TextIdentifyResponseDto> call, Response<TextIdentifyResponseDto> response) {
//                    if (response.isSuccessful()) {
//                        Toast.makeText(UploadImageActivity.this, response.message(), Toast.LENGTH_SHORT);
//
//                        TextIdentifyResponseDto responseDto = response.body();
//                        logger.info("Response : " + responseDto.getPredicted_class());
//                    } else {
//                        logger.warning(response.message());
//                        Toast.makeText(UploadImageActivity.this, response.message(), Toast.LENGTH_SHORT);
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<TextIdentifyResponseDto> call, Throwable t) {
//                    Toast.makeText(UploadImageActivity.this, t.getMessage(), Toast.LENGTH_SHORT);
//                }
//            });
        });
    }

}
