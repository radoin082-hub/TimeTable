package com.example.timetable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SpecialtyAdapter extends RecyclerView.Adapter<SpecialtyAdapter.SpecialtyHolder> {

    private Context context;
    private ArrayList<Specialty> specialties;
    SelectListener selectListener;

    public SpecialtyAdapter(Context context, ArrayList<Specialty> specialties, SelectListener selectListener) {
        this.context = context;
        this.specialties = specialties;
        this.selectListener = selectListener;
    }



    @NonNull
    @Override
    public SpecialtyAdapter.SpecialtyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_specialty, parent, false);
        return new SpecialtyAdapter.SpecialtyHolder(view);
    }



    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull SpecialtyAdapter.SpecialtyHolder holder, int position) {
        Specialty specialty = specialties.get(position);
        holder.textView.setText(specialty.getNom_spec());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectListener.onItemClicked(specialties.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return specialties.size();
    }

    public class SpecialtyHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;
        public SpecialtyHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.specialty_item);
            cardView = itemView.findViewById(R.id.cardViewSpecialty);
        }
    }
}
