package com.example.spike.franceconnect.openidconnect.example;

public class URLBuilder {
    private final String basePath;

    public URLBuilder(String basePath) {

        this.basePath = basePath;
    }


    public String addRest(String rest) {
        return basePath + rest;
    }
}
