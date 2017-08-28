package com.example.demo.model;

import javax.naming.ServiceUnavailableException;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the TimeExport POJO.
 */
public class TimeExportTest {

    @Test
    public void testFormattingTimeAndTImeZone() throws ServiceUnavailableException {
        TimeExport timeExport = new TimeExport("2017-08-27T14:24:01Z");
        assertEquals("8:24:01 AM", timeExport.getTime());
        assertEquals("America/Edmonton", timeExport.getTimezone());

        timeExport = new TimeExport("2017-08-27T17:24:01Z");
        assertEquals("11:24:01 AM", timeExport.getTime());
    }

    @Test
    public void testConvertionForDaylightSavings() throws ServiceUnavailableException {
        TimeExport timeExport = new TimeExport("2017-01-27T14:24:01Z");
        assertEquals("7:24:01 AM", timeExport.getTime());
        assertEquals("America/Edmonton", timeExport.getTimezone());
    }

    @Test
    public void testThrowsExceptionForBadDate() {
        try {
            TimeExport timeExport = new TimeExport("bad date");
            assertTrue("should have thrown an exception", false);
        } catch (ServiceUnavailableException e) {
            // expected result
        }
    }

}