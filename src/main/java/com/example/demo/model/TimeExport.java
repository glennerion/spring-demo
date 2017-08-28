package com.example.demo.model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.naming.ServiceUnavailableException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(TimeExport.class);

    /**
     * Create a time object from the date passed in.
     * @param dateString The date as a string formatted as "2017-08-27T13:20:28Z"
     * @throws ServiceUnavailableException The date provided by the service could not be parse.
     */
    public TimeExport(String dateString) throws ServiceUnavailableException {
        try {
            Instant instant = Instant.parse(dateString);

            ZonedDateTime zonedDateTime = instant.atZone(calgaryZoneId);
            time = timeFormatter.format(zonedDateTime);
            timezone = calgaryZoneId.toString();
        } catch (DateTimeParseException ex) {
            logger.error("Could not parse the date: " + dateString, ex);
            throw new ServiceUnavailableException("Date could not be parsed");
        }
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
