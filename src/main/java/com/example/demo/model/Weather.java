package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private Query query;

    public String getCreatedTime() {
        return query.getCreated();
    }
}

class Query {
    private int count;
    private String created;
    private String lang;

    String getCreated() {
        return created;
    }
}
