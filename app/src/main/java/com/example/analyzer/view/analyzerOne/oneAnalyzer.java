package com.example.analyzer.view.analyzerOne;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.analyzer.R;
import com.example.analyzer.adapter.analyzerAdapter;
import com.example.analyzer.model.modelAnalyze;
import com.example.analyzer.presenter.lexicalAnalysisPresenter;
import com.example.analyzer.utilities.Constants;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class oneAnalyzer extends AppCompatActivity {

    TextInputLayout input_text;
    RecyclerView mRecyclerView;
    TextView text_analyze;
    EditText error_analyzer;
    Button tokensView;
    String code;
    ArrayList<modelAnalyze> lexicon;
    List<String> control;
    private lexicalAnalysisPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_analyzer);

        presenter = new lexicalAnalysisPresenter();

        input_text = findViewById(R.id.input_text);
        text_analyze = findViewById(R.id.text_analyze);
        text_analyze.setVisibility(View.GONE);
        error_analyzer = findViewById(R.id.error_analyzer);
        error_analyzer.setVisibility(View.GONE);
        tokensView = findViewById(R.id.tokensView);
        tokensView.setVisibility(View.GONE);

        findViewById(R.id.gooAnalyzer).setOnClickListener(v -> {
            lexicon = new ArrayList<>();
            control = new ArrayList<>();
            code = Objects.requireNonNull(input_text.getEditText()).getText().toString().trim();
            if (!validate(code)) {
                return;
            }
            analyzer(code);
            tokensView.setVisibility(View.VISIBLE);
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

    public void analyzer(String code) {

        lexicon.clear();

        String an = code.replace(" ", "");

        List<String> TDS = new ArrayList<>();
        StringBuilder mistakes = new StringBuilder();

        for (int n = 0; n < an.length(); n++) {
            char cara = an.charAt(n);

            if (presenter.analyze(Constants.identifiers, String.valueOf(cara))) {
                checkLexicon(cara, Constants.identifiersP, R.string.identifiersP);
                addIndex(TDS, cara);
            }

            if (presenter.analyze(Constants.numeric_constants, String.valueOf(cara))) {
                checkLexicon(cara, Constants.digitP, R.string.digitP);
                addIndex(TDS, cara);
            }

            if (presenter.analyze(Constants.operators, String.valueOf(cara))) {
                checkLexicon(cara, Constants.operatorsP, R.string.operatorsP);
                addIndex(TDS, cara);
            }

            if (presenter.analyze(Constants.assignment_symbol, String.valueOf(cara))) {
                checkLexicon(cara, Constants.assignmentP, R.string.assignmentP);
            }

            if (presenter.analyze(Constants.parenthesis, String.valueOf(cara))) {
                checkLexicon(cara, Constants.groupersP, R.string.groupersP);
            }

            if (presenter.analyze(Constants.sentence_separator, String.valueOf(cara))) {
                checkLexicon(cara, Constants.separatorP, R.string.separatorP);
            }

            if (presenter.analyze(Constants.block_indicators, String.valueOf(cara))) {
                checkLexicon(cara, Constants.groupersP, R.string.groupersP);
            }

            if (presenter.analyze(Constants.reserved_words, String.valueOf(cara))) {
                if (checkL(String.valueOf(cara))) {
                    control.add(String.valueOf(cara));
                    lexicon.add(new modelAnalyze(String.valueOf(cara), String.valueOf(cara), String.valueOf(cara)));
                } else {
                    add(String.valueOf(cara), String.valueOf(cara));
                }
            }

            if (presenter.analyze(Constants.relations, String.valueOf(cara))) {
                checkLexicon(cara, Constants.relationsP, R.string.relationsP);
                addIndex(TDS, cara);
            }

            if (!presenter.analyze(Constants.error, String.valueOf(cara))) {
                text_analyze.setVisibility(View.VISIBLE);
                error_analyzer.setVisibility(View.VISIBLE);
                mistakes.append(cara).append(" ");
            }
        }

        error_analyzer.setText(mistakes.toString());
        analyzerAdapter mAdapter = new analyzerAdapter(lexicon);
        mRecyclerView.setAdapter(mAdapter);
        tokensView.setOnClickListener(v -> {
            Intent intent = new Intent(oneAnalyzer.this, tokenSequence.class);
            intent.putExtra("token", (Serializable) TDS);
            intent.putExtra("code", code);
            startActivity(intent);
        });
    }

    private void addIndex(List<String> TDS, char cara) {
        if (!TDS.contains(String.valueOf(cara))) {
            TDS.add(String.valueOf(cara));
        }
    }

    private void checkLexicon(char cara, String identifiersP, int p) {
        if (checkL(identifiersP)) {
            control.add(identifiersP);
            lexicon.add(new modelAnalyze(String.valueOf(cara), identifiersP, getString(p)));
        } else {
            add(String.valueOf(cara), identifiersP);
        }
    }

    boolean checkL(String s) {
        Set<String> set = new HashSet<>(control);
        return !set.contains(s);
    }

    void add(String lex, String pat) {
        for (modelAnalyze a : lexicon) {
            String d = a.getLex();
            String pa = a.getPat();
            if (pa.contains(pat) && !d.contains(lex)) {
                a.setLex(d + "," + lex);
            }
        }
    }
}