package com.example.rp_project_frontend;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rp_project_frontend.Model.KnowledgebaseUpdateRequestDto;
import com.example.rp_project_frontend.Model.KnowledgebaseUpdateResponseDto;
import com.example.rp_project_frontend.Retrofit.MLRetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KnowledgebaseActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutList;
    Button buttonAdd, submitknowledge;
    List<String> symptomsList = new ArrayList<>();
    EditText diseaseEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledgebase);

        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.buttonadd);
        diseaseEditText = findViewById(R.id.disease);
        submitknowledge = findViewById(R.id.submitknowledge);

        buttonAdd.setOnClickListener(this);
        String symptoms = "test1,test2,test3";
        submitknowledge.setOnClickListener(v -> {
            String disease = diseaseEditText.getText().toString();
            KnowledgebaseUpdateRequestDto knowledgebaseUpdateRequestDto = new KnowledgebaseUpdateRequestDto(disease, symptoms);

            Call<KnowledgebaseUpdateResponseDto> call = MLRetrofitClient.getInstance().getDiseaseIdentifyEndpoint().updateKnowledgeBase(knowledgebaseUpdateRequestDto);

            call.enqueue(new Callback<KnowledgebaseUpdateResponseDto>() {
                @Override
                public void onResponse(Call<KnowledgebaseUpdateResponseDto> call, Response<KnowledgebaseUpdateResponseDto> response) {
                    if (response.isSuccessful()) {
                        AlertDialog alertDialog = new AlertDialog.Builder(KnowledgebaseActivity.this).create();
                        alertDialog.setTitle("Success");
                        alertDialog.setMessage("Disease and Symptoms added successfully");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                (dialog, which) -> dialog.dismiss());
                        alertDialog.show();
                    } else {
                        Toast.makeText(KnowledgebaseActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<KnowledgebaseUpdateResponseDto> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        });
    }


    @Override
    public void onClick(View v) {
        addView();
    }

    private void addView() {

        View symptomView = getLayoutInflater().inflate(R.layout.addsymptoms, null, false);

        EditText editText = symptomView.findViewById(R.id.edit_symptomsname);
        ImageView imageclose = symptomView.findViewById(R.id.cancel);

        layoutList.addView(symptomView);
        String symptom = editText.getText().toString();
        if (!symptom.isEmpty()) {
            symptomsList.add(symptom);
            layoutList.addView(symptomView);

        }

        imageclose.setOnClickListener(view -> removeView(symptomView));

    }

    private void removeView(View view) {
        layoutList.removeView(view);
    }
}