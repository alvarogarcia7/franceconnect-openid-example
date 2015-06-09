package com.example.spike.franceconnect.openidconnect.example;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class URLBuilder {
    private final String basePath;
    private Map<String, String> parameters;

    public URLBuilder(String basePath) {
        this.basePath = basePath;
        this.parameters = new HashMap<String, String>();
    }


    public String addRest(String rest) {
        return basePath + "?" + parameters() + rest;
    }

    private String parameters() {
        String result = null;
        for (String name : parameters.keySet()) {
            String value = parameters.get(name);
            result = name + "=" + value;
        }
        return result;

    }

    public URLBuilder parameter(String name, String value) {
        this.parameters.put(name, value);
        return this;
    }
}
