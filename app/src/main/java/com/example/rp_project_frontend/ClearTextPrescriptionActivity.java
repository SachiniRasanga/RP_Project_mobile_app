package com.example.rp_project_frontend;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.rp_project_frontend.Model.DownloadImageRequestDto;
import com.example.rp_project_frontend.Model.DownloadImageResponseDto;
import com.example.rp_project_frontend.Model.VoiceRequestDto;
import com.example.rp_project_frontend.Model.VoiceResponseDto;
import com.example.rp_project_frontend.Retrofit.MLRetrofitClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClearTextPrescriptionActivity extends AppCompatActivity {

    TextView clearTextDisplaySpace;
    Button downloadClearText, playPresSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_text_prescription);

        Logger logger = Logger.getLogger("Clear Text Prescription Activity");

        clearTextDisplaySpace = findViewById(R.id.clear_text_view);
        downloadClearText = findViewById(R.id.downloadClearText);
        playPresSound = findViewById(R.id.playPresSound);

        Intent uploadImageIntent = getIntent();
        ArrayList<String> predicted_class = uploadImageIntent.getStringArrayListExtra("predicted_class");

        String identifiedText = "";
        for (String s : predicted_class) {
            identifiedText = identifiedText + "\n" + s;
        }
        clearTextDisplaySpace.setText(identifiedText);

        String finalIdentifiedText = identifiedText;
        downloadClearText.setOnClickListener(view -> {
            logger.info("Download clear text button clicked");

            DownloadImageRequestDto downloadImageRequestDto = new DownloadImageRequestDto(finalIdentifiedText);
            downloadImage(downloadImageRequestDto);
        });

        playPresSound.setOnClickListener(view -> {
            logger.info("Play prescription sound button clicked");

            String text = clearTextDisplaySpace.getText().toString();
            VoiceRequestDto voiceRequestDto = new VoiceRequestDto(text);

            Call<VoiceResponseDto> call = MLRetrofitClient
                    .getInstance()
                    .getTextIdentifyEndpoint()
                    .textToSpeech(voiceRequestDto);

            call.enqueue(new Callback<VoiceResponseDto>() {
                @Override
                public void onResponse(Call<VoiceResponseDto> call, Response<VoiceResponseDto> response) {
                    if (response.isSuccessful()) {

                        VoiceResponseDto voiceResponseDto = response.body();
                        String voiceBase64 = voiceResponseDto.getVoice();

                        byte[] audioData = Base64.decode(voiceBase64, Base64.DEFAULT);
                        File audioFile = new File(getExternalCacheDir(), "temp_audio.mp3");
                        try (FileOutputStream fos = new FileOutputStream(audioFile)) {
                            fos.write(audioData);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        MediaPlayer mediaPlayer = new MediaPlayer();
                        try {
                            mediaPlayer.setDataSource(audioFile.getPath());
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(ClearTextPrescriptionActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ClearTextPrescriptionActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<VoiceResponseDto> call, Throwable t) {
                    Toast.makeText(ClearTextPrescriptionActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void downloadImage(DownloadImageRequestDto downloadImageRequestDto) {
        Call<DownloadImageResponseDto> call = MLRetrofitClient.getInstance().getTextIdentifyEndpoint().textToImage(downloadImageRequestDto);

        Logger logger = Logger.getLogger("Download Image");
        call.enqueue(new Callback<DownloadImageResponseDto>() {
            @Override
            public void onResponse(Call<DownloadImageResponseDto> call, Response<DownloadImageResponseDto> response) {
                if (response.isSuccessful()) {
                    logger.info("Download image response successful");
                    DownloadImageResponseDto downloadImageResponseDto = response.body();
                    String image = downloadImageResponseDto.getImage();
                    logger.info("Image url: " + image);

                    byte[] imageBytes = Base64.decode(image, Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                    //Save Image to Gallery
                    saveImageToGallery(ClearTextPrescriptionActivity.this, bitmap, "Prescription", "Prescription");

                    Toast.makeText(ClearTextPrescriptionActivity.this, downloadImageResponseDto.getResponse(), Toast.LENGTH_SHORT).show();
                } else {
                    logger.warning("Download image response unsuccessful");
                    Toast.makeText(ClearTextPrescriptionActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DownloadImageResponseDto> call, Throwable t) {
                Toast.makeText(ClearTextPrescriptionActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveImageToGallery(Context context, Bitmap bitmap, String title, String description) {
        String fileName = title + ".png";

        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File imageFile = new File(directory, fileName);

        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }

            OutputStream outputStream = new FileOutputStream(imageFile);

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

            outputStream.close();

            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, title);
            values.put(MediaStore.Images.Media.DESCRIPTION, description);
            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
            values.put(MediaStore.Images.ImageColumns.BUCKET_ID, imageFile.toString().toLowerCase().hashCode());
            values.put(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, imageFile.getName().toLowerCase());
            values.put("_data", imageFile.getAbsolutePath());

            context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            Toast.makeText(context, "Image saved to gallery", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}