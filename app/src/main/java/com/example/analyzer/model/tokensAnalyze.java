package com.example.analyzer.model;

public class tokensAnalyze {
    String token;
    String tds;

    public tokensAnalyze() {

    }

    public tokensAnalyze(String token, String tds) {
        this.token = token;
        this.tds = tds;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTds() {
        return tds;
    }

    public void setTds(String tds) {
        this.tds = tds;
    }
}
