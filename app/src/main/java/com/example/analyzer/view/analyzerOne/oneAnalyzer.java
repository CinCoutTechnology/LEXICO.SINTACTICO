package com.example.analyzer.view.analyzerOne;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.analyzer.R;
import com.example.analyzer.adapter.analyzerAdapter;
import com.example.analyzer.model.modelAnalyze;
import com.example.analyzer.utilities.Constants;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class oneAnalyzer extends AppCompatActivity {

    TextInputLayout input_text;
    RecyclerView mRecyclerView;
    TextView text_analyze;
    EditText error_analyzer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_analyzer);

        input_text = findViewById(R.id.input_text);
        text_analyze = findViewById(R.id.text_analyze);
        text_analyze.setVisibility(View.GONE);
        error_analyzer = findViewById(R.id.error_analyzer);
        error_analyzer.setVisibility(View.GONE);

        findViewById(R.id.gooAnalyzer).setOnClickListener(v -> {
            String code = input_text.getEditText().getText().toString().trim();
            if (!validate(code)) {
                return;
            }
            analyzer(code);
        });

        mRecyclerView = findViewById(R.id.recycler_id);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private boolean validate(String date) {

        if (date.isEmpty()) {
            input_text.setError(getString(R.string.error));
            return false;
        } else {
            input_text.setError(null);
            input_text.setErrorEnabled(false);
            return true;
        }
    }

    private void analyzer(String code) {

        String an = code.replace(" ", "");

        List<modelAnalyze> lexicon = new ArrayList<>();

        StringBuilder mistakes = new StringBuilder();
        //M { R a; R b; c = a + b - 2 ; W c }
        for (int n = 0; n < an.length(); n++) {
            char cara = an.charAt(n);
            //M
            boolean i = Arrays.asList(Constants.identifiers).contains(String.valueOf(cara));
            if (i) {
                lexicon.add(new modelAnalyze(String.valueOf(cara), getString(R.string.identifiers)));
            }

            boolean nc = Arrays.asList(Constants.numeric_constants).contains(String.valueOf(cara));
            if (nc) {
                lexicon.add(new modelAnalyze(String.valueOf(cara), getString(R.string.numeric_constants)));
            }

            boolean o = Arrays.asList(Constants.operators).contains(String.valueOf(cara));
            if (o) {
                lexicon.add(new modelAnalyze(String.valueOf(cara), getString(R.string.operators)));
            }

            boolean as = Arrays.asList(Constants.assignment_symbol).contains(String.valueOf(cara));
            if (as) {
                lexicon.add(new modelAnalyze(String.valueOf(cara), getString(R.string.assignment_symbol)));
            }

            boolean par = Arrays.asList(Constants.parenthesis).contains(String.valueOf(cara));
            if (par) {
                lexicon.add(new modelAnalyze(String.valueOf(cara), getString(R.string.parenthesis)));
            }

            boolean ss = Arrays.asList(Constants.sentence_separator).contains(String.valueOf(cara));
            if (ss) {
                lexicon.add(new modelAnalyze(String.valueOf(cara), getString(R.string.sentence_separator)));
            }

            boolean bi = Arrays.asList(Constants.block_indicators).contains(String.valueOf(cara));
            if (bi) {
                lexicon.add(new modelAnalyze(String.valueOf(cara), getString(R.string.block_indicators)));
            }

            boolean rw = Arrays.asList(Constants.reserved_words).contains(String.valueOf(cara));
            if (rw) {
                lexicon.add(new modelAnalyze(String.valueOf(cara), getString(R.string.reserved_words)));
            }

            boolean error = Arrays.asList(Constants.error).contains(String.valueOf(cara));
            if (!error) {
                text_analyze.setVisibility(View.VISIBLE);
                error_analyzer.setVisibility(View.VISIBLE);
                mistakes.append(cara).append(" ");
            }
        }
        error_analyzer.setText(mistakes.toString());

        analyzerAdapter mAdapter = new analyzerAdapter(lexicon);
        mRecyclerView.setAdapter(mAdapter);
    }
}