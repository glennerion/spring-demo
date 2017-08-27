package com.example.demo.model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The time export POJO for returning time in JSON format.
 */
public class TimeExport {
    /**
     * The time.
     */
    private String time;
    /**
     * The timezone.
     */
    private String timezone;

    /**
     * Hard coded timezone for Calgary
     */
    private static final ZoneId calgaryZoneId = ZoneId.of("America/Edmonton");
    /**
     * The formatter to output the time
     */
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm:ss a");

    /**
     * Create a time object from the date passed in.
     * @param dateString The date as a string formatted as "2017-08-27T13:20:28Z"
     */
    public TimeExport(String dateString) {
        Instant instant = Instant.parse(dateString);

        ZonedDateTime zonedDateTime = instant.atZone(calgaryZoneId);
        time = timeFormatter.format(zonedDateTime);
        timezone = calgaryZoneId.toString();
    }

    /**
     * Get the time.
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * Get the date.
     * @return the date
     */
    public String getTimezone() {
        return timezone;
    }
}
