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

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.levelHolder>{

    private Context context;
    private ArrayList<Levle> levles;
    SelectListener selectListener;

    public LevelAdapter(Context context, ArrayList<Levle> levles, SelectListener selectListener) {
        this.context = context;
        this.levles = levles;
        this.selectListener = selectListener;
    }

    @NonNull
    @Override
    public LevelAdapter.levelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_level, parent, false);
        return new LevelAdapter.levelHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull levelHolder holder, int position) {
        Levle levle = levles.get(position);

        if(Integer.parseInt(levle.getId_niveau())<=3){
        holder.textView.setText("L"+levle.getId_niveau());
        }
        else {
            if(Integer.parseInt(levle.getId_niveau())==4){
                holder.textView.setText("M1");
            }
            else {
                if(Integer.parseInt(levle.getId_niveau())==5){
                    holder.textView.setText("M2");
                }
            }
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectListener.onItemClicked(levles.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return levles.size();
    }

    public class levelHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;
        public levelHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.level_item);
            cardView = itemView.findViewById(R.id.cardViewLevel);
        }
    }
}
