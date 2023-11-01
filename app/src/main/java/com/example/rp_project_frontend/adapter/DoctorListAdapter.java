package com.example.rp_project_frontend.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rp_project_frontend.Model.Doctor;
import com.example.rp_project_frontend.R;
import com.example.rp_project_frontend.RecommendedDoctorDetailsActivity;

import java.util.List;

public class DoctorListAdapter extends BaseAdapter {

    private Context context;
    private List<Doctor> doctorList;

    public DoctorListAdapter(Context context, List<Doctor> doctorList) {
        this.context = context;
        this.doctorList = doctorList;
    }

    @Override
    public int getCount() {
        return doctorList.size();
    }

    @Override
    public Object getItem(int i) {
        return doctorList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.doctor_list_item, viewGroup, false);
        }

        TextView doctorNameTextView = view.findViewById(R.id.doctor_name_view);
        ImageButton doctorViewBtn = view.findViewById(R.id.doctor_view_eye_btn);
        ImageView doctorImageView = view.findViewById(R.id.doctor_image_view);

        Doctor doctor = doctorList.get(i);

        doctorNameTextView.setText(doctor.getFullname());

        doctorViewBtn.setOnClickListener(view1 -> {
            Intent doctorDetailsIntent = new Intent(context, RecommendedDoctorDetailsActivity.class);
            doctorDetailsIntent.putExtra("doctorId", doctor.getId());
            context.startActivity(doctorDetailsIntent);
        });

        return view;
    }

    public void addAll(List<Doctor> doctors) {
        doctorList.addAll(doctors);
        notifyDataSetChanged();
    }
}
