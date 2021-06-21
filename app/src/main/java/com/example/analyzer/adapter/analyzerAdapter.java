package com.example.analyzer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.analyzer.R;
import com.example.analyzer.model.modelAnalyze;

import java.util.List;

public class analyzerAdapter extends RecyclerView.Adapter<analyzerAdapter.viewHolder> {

    List<modelAnalyze> analyzes;

    public analyzerAdapter(List<modelAnalyze> analyzes) {
        this.analyzes = analyzes;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_code, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        modelAnalyze analyze = analyzes.get(position);

        holder.text_lex.setText(analyze.getLex());
        holder.text_tok.setText(analyze.getTok());

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

        TextView text_lex, text_tok;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            text_lex = itemView.findViewById(R.id.text_lex);
            text_tok = itemView.findViewById(R.id.text_tok);
        }
    }
}
