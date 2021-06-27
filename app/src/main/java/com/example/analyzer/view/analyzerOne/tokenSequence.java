package com.example.analyzer.view.analyzerOne;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.analyzer.R;
import com.example.analyzer.adapter.sequenceAdapter;
import com.example.analyzer.adapter.simAdapter;
import com.example.analyzer.model.modelAnalyze;
import com.example.analyzer.model.tokensAnalyze;
import com.example.analyzer.presenter.lexicalAnalysisPresenter;
import com.example.analyzer.utilities.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class tokenSequence extends AppCompatActivity {


    RecyclerView mRecyclerView;
    RecyclerView mRecyclerViewSequence;
    private lexicalAnalysisPresenter presenter;
    private ArrayList<tokensAnalyze> tokensAnalyzesList;
    List<String> TDS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_sequence);

        presenter = new lexicalAnalysisPresenter();
        tokensAnalyzesList = new ArrayList<>();

        //noinspection unchecked
        TDS = (List<String>) getIntent().getSerializableExtra("token");
        String code = getIntent().getStringExtra("code");

        TextView co = findViewById(R.id.code);
        co.setText(code);

        assert code != null;
        assert TDS != null;
        mRecyclerView = findViewById(R.id.list_si);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerViewSequence = findViewById(R.id.list_se);

        mRecyclerViewSequence.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(tokenSequence.this);
        mRecyclerViewSequence.setLayoutManager(layoutManager);
        RecyclerView.LayoutManager manager = new GridLayoutManager(tokenSequence.this, getResources().getInteger(R.integer.number_of_grid_items));
        mRecyclerViewSequence.setLayoutManager(manager);

        Token_Sequence(TDS);
        analyze_sequence(code);
    }

    private void Token_Sequence(List<String> tds) {
        simAdapter mAdapter = new simAdapter(tds);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void analyze_sequence(String code) {

        String an = code.replace(" ", "");

        for (int n = 0; n < an.length(); n++) {
            char sec = an.charAt(n);

            if (presenter.analyze(Constants.identifiers, String.valueOf(sec))) {
                if (TDS.contains(String.valueOf(sec))) {
                    String po = String.valueOf(TDS.indexOf(String.valueOf(sec)));
                    tokensAnalyzesList.add(new tokensAnalyze(getString(R.string.identifiersP), po));
                } else {
                    tokensAnalyzesList.add(new tokensAnalyze(getString(R.string.identifiersP), " "));
                }
            }
            if (presenter.analyze(Constants.numeric_constants, String.valueOf(sec))) {

                if (TDS.contains(String.valueOf(sec))) {
                    String po = String.valueOf(TDS.indexOf(String.valueOf(sec)));
                    tokensAnalyzesList.add(new tokensAnalyze(getString(R.string.digitP), po));
                } else {
                    tokensAnalyzesList.add(new tokensAnalyze(getString(R.string.digitP), " "));
                }
            }

            if (presenter.analyze(Constants.operators, String.valueOf(sec))) {
                if (TDS.contains(String.valueOf(sec))) {
                    String po = String.valueOf(TDS.indexOf(String.valueOf(sec)));
                    tokensAnalyzesList.add(new tokensAnalyze(getString(R.string.operatorsP), po));
                } else {
                    tokensAnalyzesList.add(new tokensAnalyze(getString(R.string.operatorsP), " "));
                }
            }

            if (presenter.analyze(Constants.assignment_symbol, String.valueOf(sec))) {
                if (TDS.contains(String.valueOf(sec))) {
                    String po = String.valueOf(TDS.indexOf(String.valueOf(sec)));
                    tokensAnalyzesList.add(new tokensAnalyze(getString(R.string.assignmentP), po));
                } else {
                    tokensAnalyzesList.add(new tokensAnalyze(getString(R.string.assignmentP), " "));
                }
            }

            if (presenter.analyze(Constants.parenthesis, String.valueOf(sec))) {
                if (TDS.contains(String.valueOf(sec))) {
                    String po = String.valueOf(TDS.indexOf(String.valueOf(sec)));
                    tokensAnalyzesList.add(new tokensAnalyze(getString(R.string.groupersP), po));
                } else {
                    tokensAnalyzesList.add(new tokensAnalyze(getString(R.string.groupersP), " "));
                }
            }

            if (presenter.analyze(Constants.sentence_separator, String.valueOf(sec))) {
                if (TDS.contains(String.valueOf(sec))) {
                    String po = String.valueOf(TDS.indexOf(String.valueOf(sec)));
                    tokensAnalyzesList.add(new tokensAnalyze(getString(R.string.separatorP), po));
                } else {
                    tokensAnalyzesList.add(new tokensAnalyze(getString(R.string.separatorP), " "));
                }
            }

            if (presenter.analyze(Constants.block_indicators, String.valueOf(sec))) {
                if (TDS.contains(String.valueOf(sec))) {
                    String po = String.valueOf(TDS.indexOf(String.valueOf(sec)));
                    tokensAnalyzesList.add(new tokensAnalyze(getString(R.string.groupersP), po));
                } else {
                    tokensAnalyzesList.add(new tokensAnalyze(getString(R.string.groupersP), " "));
                }
            }

            if (presenter.analyze(Constants.reserved_words, String.valueOf(sec))) {

                if (TDS.contains(String.valueOf(sec))) {
                    String po = String.valueOf(TDS.indexOf(String.valueOf(sec)));
                    tokensAnalyzesList.add(new tokensAnalyze(String.valueOf(sec), po));
                } else {
                    tokensAnalyzesList.add(new tokensAnalyze(String.valueOf(sec), " "));
                }
            }

            if (presenter.analyze(Constants.relations, String.valueOf(sec))) {
                if (TDS.contains(String.valueOf(sec))) {
                    String po = String.valueOf(TDS.indexOf(String.valueOf(sec)));
                    tokensAnalyzesList.add(new tokensAnalyze(getString(R.string.relationsP), po));
                } else {
                    tokensAnalyzesList.add(new tokensAnalyze(getString(R.string.relationsP), " "));
                }
            }

        }
        sequenceAdapter mAdapter = new sequenceAdapter(tokensAnalyzesList);
        mRecyclerViewSequence.setAdapter(mAdapter);

    }

    boolean checkL(String s) {
        Set<String> set = new HashSet<>(TDS);
        return !set.contains(s);
    }
}