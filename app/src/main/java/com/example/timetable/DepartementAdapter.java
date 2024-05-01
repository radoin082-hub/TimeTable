package com.example.timetable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DepartementAdapter extends RecyclerView.Adapter<DepartementAdapter.DepartementHolder> {

    private Context context;
    private ArrayList<Departement> departements;
    SelectListener selectListener;

    public DepartementAdapter(Context context, ArrayList<Departement> departements, SelectListener selectListener) {
        this.context = context;
        this.departements = departements;
        this.selectListener = selectListener;
    }

    @NonNull
    @Override



    public DepartementAdapter.DepartementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_departement, parent, false);
        return new DepartementAdapter.DepartementHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull DepartementAdapter.DepartementHolder holder, int position) {
        Departement departement = departements.get(position);
        holder.textView.setText(departement.getName_fr());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectListener.onItemClicked(departements.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return departements.size();
    }

    public class DepartementHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;
        public DepartementHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView1);

            cardView = itemView.findViewById(R.id.cardView2);


        }
    }
}
