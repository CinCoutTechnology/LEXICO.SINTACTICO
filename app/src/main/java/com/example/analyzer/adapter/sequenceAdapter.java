package com.example.analyzer.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.analyzer.R;
import com.example.analyzer.model.modelAnalyze;
import com.example.analyzer.model.tokensAnalyze;

import java.util.ArrayList;

public class sequenceAdapter extends RecyclerView.Adapter<sequenceAdapter.viewHolder> {

    ArrayList<tokensAnalyze> analyzes;
    int selectedPosition = -1;

    public sequenceAdapter(ArrayList<tokensAnalyze> analyzes) {
        this.analyzes = analyzes;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sect, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        tokensAnalyze analyze = analyzes.get(position);

        holder.tok.setText("<" + analyze.getToken() + ",");
        holder.id.setText(analyze.getTds() + ">");

        if (selectedPosition == position)
            holder.itemView.setBackgroundColor(Color.parseColor("#f6e8ea"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#e4f2f3"));

        holder.itemView.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();

        });
    }

    @Override
    public int getItemCount() {
        return analyzes.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class viewHolder extends RecyclerView.ViewHolder {

        TextView tok, id;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            tok = itemView.findViewById(R.id.tok);

        }

    }
}
