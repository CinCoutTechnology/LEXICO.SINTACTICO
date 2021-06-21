package com.example.analyzer.model;

public class modelAnalyze {

    String lex;
    String tok;

    public modelAnalyze(String lex, String tok) {
        this.lex = lex;
        this.tok = tok;
    }

    public modelAnalyze() {

    }

    public String getLex() {
        return lex;
    }

    public void setLex(String lex) {
        this.lex = lex;
    }

    public String getTok() {
        return tok;
    }

    public void setTok(String tok) {
        this.tok = tok;
    }
}
