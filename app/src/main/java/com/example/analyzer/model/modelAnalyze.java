package com.example.analyzer.model;

import java.io.Serializable;

public class modelAnalyze implements Serializable {

    String lex;
    String pat;
    String tok;

    public modelAnalyze(String lex, String pat, String tok) {
        this.lex = lex;
        this.pat = pat;
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

    public String getPat() {
        return pat;
    }

    public void setPat(String pat) {
        this.pat = pat;
    }

    public String getTok() {
        return tok;
    }

    public void setTok(String tok) {
        this.tok = tok;
    }

}
