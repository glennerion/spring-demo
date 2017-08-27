package com.example.demo.model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeExport {
    private String time;
    private String timezone;

    private static final ZoneId calgaryZoneId = ZoneId.of("America/Edmonton");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm:ss a");

    public TimeExport(String dateString) {
        // 2017-08-27T13:20:28Z
        Instant instant = Instant.parse(dateString);

        ZonedDateTime zonedDateTime = instant.atZone(calgaryZoneId);
        time = timeFormatter.format(zonedDateTime);
        timezone = calgaryZoneId.toString();
    }

    public String getTime() {
        return time;
    }

    public String getTimezone() {
        return timezone;
    }
}
