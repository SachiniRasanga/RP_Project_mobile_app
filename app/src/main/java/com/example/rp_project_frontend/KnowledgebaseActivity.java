package com.example.rp_project_frontend;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class KnowledgebaseActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutList;

    Button buttonAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledgebase);


        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.buttonadd);

        buttonAdd.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        addView();
    }

    private void addView() {

        View symptomView = getLayoutInflater().inflate(R.layout.addsymptoms,null,false);

        EditText editText = (EditText)symptomView.findViewById(R.id.edit_symptomsname);
        ImageView imageclose = (ImageView)symptomView.findViewById(R.id.cancel) ;




        layoutList.addView(symptomView);

        imageclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView(symptomView);
            }
        });



    }

    private void removeView(View view){

        layoutList.removeView(view);

    }
}