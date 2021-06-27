package com.example.analyzer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.analyzer.R;
import com.example.analyzer.model.modelAnalyze;

import java.util.ArrayList;
import java.util.List;

public class simAdapter extends RecyclerView.Adapter<simAdapter.viewHolder> {

    List<String> TDS;

    public simAdapter(List<String> TDS) {
        this.TDS = TDS;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sim, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {


        String analyze = TDS.get(position);
        holder.text_num.setText(String.valueOf(position));
        holder.text_id.setText(analyze);
    }

    @Override
    public int getItemCount() {
        return TDS.size();
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

        TextView text_num, text_id;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            text_num = itemView.findViewById(R.id.text_num);
            text_id = itemView.findViewById(R.id.text_id);
        }

    }
}
