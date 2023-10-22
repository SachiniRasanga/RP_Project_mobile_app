package com.example.rp_project_frontend;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DashBoardActivity extends AppCompatActivity {

    LinearLayout profileDashboardBtn, logoutDashboardBtn, plantIdentifyDashboardBtn, prescriptionReadDashboardBtn, pressurePointDashboardBtn, diseaseIdentifyDashboardBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        profileDashboardBtn = findViewById(R.id.profileDashboardBtn);
        logoutDashboardBtn = findViewById(R.id.logoutDashboardBtn);
        plantIdentifyDashboardBtn = findViewById(R.id.plantIdentifyDashboardBtn);
        prescriptionReadDashboardBtn = findViewById(R.id.prescriptionReadDashboardBtn);
        pressurePointDashboardBtn = findViewById(R.id.pressurePointDashboardBtn);
        diseaseIdentifyDashboardBtn = findViewById(R.id.diseaseIdentifyDashboardBtn);

        profileDashboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(DashBoardActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
            }
        });

        logoutDashboardBtn.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(DashBoardActivity.this);
            builder.setCancelable(true);
            builder.setTitle("Logout");
            builder.setMessage("Do you want to logout ?");
            builder.setPositiveButton("Yes", (dialogInterface, i) -> {

                SharedPreferences sharedPreferences = getSharedPreferences("user_details", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("userId");
                editor.commit();
                finish();

                Toast.makeText(DashBoardActivity.this, "Logout successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DashBoardActivity.this, LoginActivity.class);
                startActivity(intent);
            });

            builder.setNegativeButton("No", (dialogInterface, i) -> {
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        });

        prescriptionReadDashboardBtn.setOnClickListener(view -> {
            Intent prescriptionReadIntent = new Intent(DashBoardActivity.this, PrescriptionUploadActivity.class);
            startActivity(prescriptionReadIntent);
        });
    }
}
