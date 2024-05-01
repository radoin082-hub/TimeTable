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

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupHolder> {

    private Context context;
    private ArrayList<Group> groups;
    private SelectListener selectListener;

    public GroupAdapter(Context context, ArrayList<Group> groups, SelectListener selectListener) {
        this.context = context;
        this.groups = groups;
        this.selectListener = selectListener;
    }

    @NonNull
    @Override
    public GroupAdapter.GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_group, parent, false);
        return new GroupAdapter.GroupHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.GroupHolder holder, int position) {
        Group group=groups.get(position);
        holder.textView.setText(group.getGroupe_name());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectListener.onItemClicked(groups.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public class GroupHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;
        public GroupHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.group_item);
            cardView = itemView.findViewById(R.id.cardViewGroup);
        }
    }
}
