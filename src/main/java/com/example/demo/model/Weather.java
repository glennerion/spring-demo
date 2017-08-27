package com.example.demo.model;

/**
 * POJO to use for the JSON from the Yahoo Weather service.
 */
public class Weather {
    /**
     * Query parameter
     */
    private Query query;

    /**
     * Get the created field
     * @return
     */
    public String getCreatedTime() {
        return query.getCreated();
    }
}

/**
 * Class to hold the query
 */
class Query {
    /**
     * get the results count
     */
    private int count;
    /**
     * Create time
     */
    private String created;

    /**
     * Return the created time.
     * @return the time.
     */
    String getCreated() {
        return created;
    }
}
