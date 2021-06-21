package com.example.analyzer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.analyzer.view.An.lexi;
import com.example.analyzer.view.analyzerOne.oneAnalyzer;
import com.example.analyzer.view.analyzerTwo.twoAnalyzer;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.analyzer_1).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, oneAnalyzer.class)));

        findViewById(R.id.analyzer_2).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, twoAnalyzer.class)));
    }
}