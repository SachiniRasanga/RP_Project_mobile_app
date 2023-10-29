package com.example.rp_project_frontend.Service;

import android.annotation.SuppressLint;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AudioRecorder {
    private static final int RECORDER_SAMPLERATE = 44100;
    private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
    private static final int RECORDER_BPP = 16;

    private AudioRecord audioRecord = null;
    private int bufferSize;
    private Thread recordingThread;
    private boolean isRecording = false;

    @SuppressLint("MissingPermission")
    public void startRecording() {
        bufferSize = AudioRecord.getMinBufferSize(RECORDER_SAMPLERATE, RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING);

        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, RECORDER_SAMPLERATE, RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING, bufferSize);


        audioRecord.startRecording();
        isRecording = true;
        recordingThread = new Thread(new Runnable() {
            public void run() {
                writeAudioDataToFile();
            }
        }, "AudioRecorder Thread");
        recordingThread.start();
    }

    private void writeAudioDataToFile() {
        byte data[] = new byte[bufferSize];
        String filePath = getRecordingFilePath();

        FileOutputStream os = null;
        try {
            os = new FileOutputStream(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (os != null) {
            while (isRecording) {
                audioRecord.read(data, 0, bufferSize);
                try {
                    os.write(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopRecording() {
        if (null != audioRecord) {
            isRecording = false;
            audioRecord.stop();
            audioRecord.release();
            audioRecord = null;
            recordingThread = null;
        }
    }

    private String getRecordingFilePath() {
        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/YourFolderName/");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        return (dir.getAbsolutePath() + "/" + System.currentTimeMillis() + ".wav");
    }
}
