package com.example.timetable;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleHolder> {

    private Context context;
    private ArrayList<Schedule> scheduleArrayList;

    public ModuleAdapter(Context context, ArrayList<Schedule> scheduleArrayList) {
        this.context = context;
        this.scheduleArrayList = scheduleArrayList;
    }

    @NonNull
    @Override
    public ModuleAdapter.ModuleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_module, parent, false);
        return new ModuleAdapter.ModuleHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ModuleAdapter.ModuleHolder holder, int position) {
        Schedule schedule = scheduleArrayList.get(position);

        holder.TypeOfCourse.setText(schedule.getTypeOfCourse());

        if(schedule.getTypeOfCourse().equals("Cours")) {
            holder.cardView.setCardBackgroundColor(R.color.sea_wave);
        } else if (schedule.getTypeOfCourse().equals("TD")) {
            holder.cardView.setCardBackgroundColor(R.color.sea_wave2);
        } else if (schedule.getTypeOfCourse().equals("TP")) {
            holder.cardView.setCardBackgroundColor(R.color.sea_wave3);
        } else if (schedule.getTypeOfCourse().equals("Workshop")) {
            holder.cardView.setCardBackgroundColor(R.color.blue);
        }

        holder.ModuleName.setText(schedule.getModuleName());
      // Convert int to String
        if(schedule.getTimeSlot()==1){
            holder.TimeSlot.setText("8:00 - 9:30");
        } else if (schedule.getTimeSlot()==2) {
            holder.TimeSlot.setText("9:40 - 11:10");
        } else if (schedule.getTimeSlot()==3) {
            holder.TimeSlot.setText("11:20 - 12:50");
        } else if (schedule.getTimeSlot()==4) {
            holder.TimeSlot.setText("13:10 - 14:40");
        } else if (schedule.getTimeSlot()==5) {
            holder.TimeSlot.setText("14:50 - 16:20");
        }

         // Convert int to String

        if(schedule.getDayOfWeek()==1){
            holder.DayOfWeek.setText("Sunday");
        } else if (schedule.getDayOfWeek()==2) {
            holder.DayOfWeek.setText("Monday");
        } else if (schedule.getDayOfWeek()==3) {
            holder.DayOfWeek.setText("Tuesday");
        } else if (schedule.getDayOfWeek()==4) {
            holder.DayOfWeek.setText("Wednesday");
        } else if (schedule.getDayOfWeek()==5) {
            holder.DayOfWeek.setText("Thursday");
        } else if (schedule.getDayOfWeek()==7) {
            holder.DayOfWeek.setText("Saturday");
        }


        holder.Location.setText(schedule.getLocation());
        holder.Location.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (schedule.getOnline() == 1) {
                    if (!schedule.getOnlineLink().isEmpty()) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(schedule.getOnlineLink()));
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, "URL Is Empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Open Google Maps with the location
                    if (!schedule.getLocationGPS().isEmpty()) {
                        String[] parts = schedule.getLocationGPS().split(",");
                        double latitude = Double.parseDouble(parts[0].trim());
                        double longitude = Double.parseDouble(parts[1].trim());

                        Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude);
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");

                        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                            context.startActivity(mapIntent);
                        } else {
                            Toast.makeText(context, "Google Maps app is not installed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "Location is not available", Toast.LENGTH_SHORT).show();
                    }
                }
            }



        });
    }

    /*private void startActivity(Intent intent) {
        startActivity(intent);
    }*/


    @Override
    public int getItemCount() {
        return scheduleArrayList.size();
    }

    public class ModuleHolder extends RecyclerView.ViewHolder {
        TextView TypeOfCourse;
        TextView ModuleName;
        TextView TimeSlot;
        TextView DayOfWeek;
        TextView Location;
        CardView cardView;
        public ModuleHolder(@NonNull View itemView) {
            super(itemView);
            TypeOfCourse = itemView.findViewById(R.id.TypeOfCourse);
            ModuleName = itemView.findViewById(R.id.ModuleName);
            TimeSlot = itemView.findViewById(R.id.TimeSlot);
            DayOfWeek = itemView.findViewById(R.id.DayOfWeek);
            Location = itemView.findViewById(R.id.Location);
            cardView = itemView.findViewById(R.id.cardViewModule);
        }
    }
}
