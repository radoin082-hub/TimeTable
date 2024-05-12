package com.example.timetable;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.FacultyHolder> {

    private Context mainActivity;
    private ArrayList<Faculty> facultyList;

    SelectListener selectListener;
    public FacultyAdapter(Context mainActivity, ArrayList<Faculty> facultyList,SelectListener selectListener) {
        this.mainActivity = mainActivity;
        this.facultyList = facultyList;
        this.selectListener=selectListener;
    }

    @NonNull
    @Override
    public FacultyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(mainActivity).inflate(R.layout.item_faculty, parent, false);
       return new FacultyHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull FacultyAdapter.FacultyHolder holder, int position) {
        Faculty faculty = facultyList.get(position);
        holder.faculty_name.setText(faculty.getName_fac());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectListener.onItemClicked(facultyList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return facultyList.size();
    }

    public class FacultyHolder extends RecyclerView.ViewHolder {
        public TextView faculty_name;
        public ImageView button;
        public CardView cardView;
        public FacultyHolder(@NonNull View itemView) {
            super(itemView);
            faculty_name =itemView.findViewById(R.id.faculty_name);
            button =itemView.findViewById(R.id.button);
            //button1=itemView.findViewById(R.id.button1);
            cardView =itemView.findViewById(R.id.main_layout);
        }
    }


}
