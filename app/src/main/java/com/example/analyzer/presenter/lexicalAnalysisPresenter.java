package com.example.analyzer.presenter;


import java.util.Arrays;

public class lexicalAnalysisPresenter {

    public boolean analyze(String[] id, String constant) {
        return Arrays.asList(id).contains(constant);
    }
}
