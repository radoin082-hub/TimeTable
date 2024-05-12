package com.example.timetable;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MariaAdapter extends RecyclerView.Adapter<MariaAdapter.MariaHolder> {

        @NonNull
    @Override
    public MariaAdapter.MariaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MariaAdapter.MariaHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MariaHolder extends RecyclerView.ViewHolder {
        public MariaHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
