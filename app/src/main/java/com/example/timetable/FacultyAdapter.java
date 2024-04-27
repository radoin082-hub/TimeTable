package com.example.timetable;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.FacultyHolder> {

    private MainActivity mainActivity;
    private ArrayList<Faculty> facultyList;

    public FacultyAdapter(MainActivity mainActivity, ArrayList<Faculty> facultyList) {
        this.mainActivity = mainActivity;
        this.facultyList = facultyList;
    }

    @NonNull
    @Override
    public FacultyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FacultyHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_faculty, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyAdapter.FacultyHolder holder, int position) {
        holder.item_faculty.setText(String.valueOf(facultyList.get(position).getId_fac()));
    }

    @Override
    public int getItemCount() {
        return facultyList.size();
    }

    public class FacultyHolder extends RecyclerView.ViewHolder {
        public TextView item_faculty;

        public FacultyHolder(@NonNull View itemView) {
            super(itemView);
            item_faculty = itemView.findViewById(R.id.item_faculty);
        }
    }


}
