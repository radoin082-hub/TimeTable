package com.example.timetable;

import static com.example.timetable.R.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionHolder>{

    private Context context;
    private ArrayList<Section> sections;
    SelectListener selectListener;

    public SectionAdapter(Context context, ArrayList<Section> sections, SelectListener selectListener) {
        this.context = context;
        this.sections = sections;
        this.selectListener = selectListener;
    }

    @NonNull
    @Override
    public SectionAdapter.SectionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_section, parent, false);
        return new SectionAdapter.SectionHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull SectionAdapter.SectionHolder holder, int position) {
        Section section = sections.get(position);
        holder.textView.setText(section.getAbrev_fr());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectListener.onItemClicked(sections.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

    public class SectionHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;
        public SectionHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.section_item);
            cardView=itemView.findViewById(R.id.cardViewSectin);
        }
    }
}
