package com.example.spike.franceconnect.openidconnect.example;

import java.util.*;

public class URLBuilder {
    private final String basePath;
    private Map<String, String> parameters;

    public URLBuilder(String basePath) {
        this.basePath = basePath;
        this.parameters = new LinkedHashMap<>();
    }


    private String parameters() {

        List<String> formattedParameters = new ArrayList<>();
        for (String name : parameters.keySet()) {
            String value = parameters.get(name);
            formattedParameters.add(name + "=" + value);
        }
        final StringJoiner joiner = new StringJoiner("&");
        formattedParameters.forEach(joiner::add);
        return joiner.toString();

    }

    public URLBuilder parameter(String name, String value) {
        this.parameters.put(name, value);
        return this;
    }

    public String build() {
        return basePath + "?" + parameters();
    }
}
