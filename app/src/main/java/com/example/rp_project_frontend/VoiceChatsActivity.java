package com.example.rp_project_frontend;

import android.annotation.SuppressLint;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.rp_project_frontend.Model.DiseaseIdentifyRequestDto;
import com.example.rp_project_frontend.Model.DiseaseIdentifyResponseDto;
import com.example.rp_project_frontend.Model.DiseaseVoiceToTextRequestDto;
import com.example.rp_project_frontend.Model.DiseaseVoiceToTextResponseDto;
import com.example.rp_project_frontend.Retrofit.MLRetrofitClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoiceChatsActivity extends AppCompatActivity {

    private static final Integer MICROPHONE_PERMISSION_CODE = 200;
    Button voiceRecordBtn;
    MediaRecorder mediaRecorder;
    Logger logger = Logger.getLogger("VoiceChatsActivity");
    String base64 = "";

    private boolean isRecording = false;
    private static final int SAMPLE_RATE = 44100; // 44.1 kHz
    private static final int CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO;
    private static final int AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;
    private static final int BUFFER_SIZE = AudioRecord.getMinBufferSize(SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT);
    private AudioRecord audioRecord;
    private FileOutputStream wavFileOutputStream;
    private File wavFile;
    private Handler stopRecordingHandler = new Handler();

    private boolean isMicrophonePresent() {
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
            return true;
        } else {
            return false;
        }
    }

    private void getMicrophonePermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            logger.info("Microphone not present");
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.RECORD_AUDIO}, MICROPHONE_PERMISSION_CODE);
        } else {
            logger.info("Microphone present");
        }
    }

    private String getRecordingFilePath() {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(musicDirectory, "voice_record.wav");
        return file.getPath();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_chats);

        if (isMicrophonePresent()) {
            getMicrophonePermission();
        }

        voiceRecordBtn = findViewById(R.id.voiceRecordBtn);

        voiceRecordBtn.setOnClickListener(v -> {
            recordVoice();
        });
    }

    public void recordVoice() {
        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(getRecordingFilePath());
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.prepare();
            mediaRecorder.start();

            Toast.makeText(this, "Recording is started", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(() -> {
                try {
                    stopRecording();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }, 10000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopRecording() throws IOException {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;

            logger.info("Path : " + getRecordingFilePath());
            Toast.makeText(this, "Recording is stopped", Toast.LENGTH_SHORT).show();

            base64 = convertAudioToBase64(getRecordingFilePath());

            DiseaseVoiceToTextRequestDto diseaseVoiceToTextRequestDto = new DiseaseVoiceToTextRequestDto(base64);
            Call<DiseaseVoiceToTextResponseDto> call = MLRetrofitClient.getInstance().getDiseaseIdentifyEndpoint().voiceToText(diseaseVoiceToTextRequestDto);

            call.enqueue(new Callback<DiseaseVoiceToTextResponseDto>() {
                @Override
                public void onResponse(Call<DiseaseVoiceToTextResponseDto> call, Response<DiseaseVoiceToTextResponseDto> response) {
                    if (response.isSuccessful()) {
                        logger.info("Success: " + response.body().getResponse());
                        predictDisease(new DiseaseIdentifyRequestDto(response.body().getResponse()));
                    } else {
                        logger.warning("Error : " + response.errorBody());
                        AlertDialog.Builder builder = new AlertDialog.Builder(VoiceChatsActivity.this);
                        builder.setTitle("Error");
                        builder.setMessage(response.errorBody().toString());
                        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
                        builder.show();
                    }
                }

                @Override
                public void onFailure(Call<DiseaseVoiceToTextResponseDto> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }

    }

    public static String convertAudioToBase64(String filePath) {
        File audioFile = new File(filePath);

        if (audioFile.exists()) {
            try {
                // Read the audio file into a byte array
                FileInputStream inputStream = new FileInputStream(audioFile);
                byte[] audioData = new byte[(int) audioFile.length()];
                inputStream.read(audioData);
                inputStream.close();

                // Convert the byte array to a Base64 string
                String base64String = Base64.getEncoder().encodeToString(audioData);
                return base64String;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void predictDisease(DiseaseIdentifyRequestDto diseaseIdentifyRequestDto) {

        Call<DiseaseIdentifyResponseDto> call = MLRetrofitClient.getInstance().getDiseaseIdentifyEndpoint().predictDisease(diseaseIdentifyRequestDto);
        call.enqueue(new Callback<DiseaseIdentifyResponseDto>() {
            @Override
            public void onResponse(Call<DiseaseIdentifyResponseDto> call, Response<DiseaseIdentifyResponseDto> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("200")) {
                        logger.info("Disease : " + response);
                        logger.log(java.util.logging.Level.INFO, "Identified Disease : " + response.body().getResponse());

                        Intent voiceChatIntent = new Intent(VoiceChatsActivity.this, DiseaseViewActivity.class);
                        voiceChatIntent.putExtra("disease", response.body().getResponse().getDisease());
                        voiceChatIntent.putExtra("doctor", response.body().getResponse().getDoctor());
                        voiceChatIntent.putExtra("message", response.body().getResponse().getMessage());
                        startActivity(voiceChatIntent);
                    } else {
                        Toast.makeText(VoiceChatsActivity.this, "Error: " + response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(VoiceChatsActivity.this, "Error: " + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DiseaseIdentifyResponseDto> call, Throwable t) {
                Toast.makeText(VoiceChatsActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}