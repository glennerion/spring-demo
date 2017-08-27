package com.example.demo.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimeExportTest {

    @Test
    public void testFormattingTimeAndTImeZone() {
        TimeExport timeExport = new TimeExport("2017-08-27T14:24:01Z");
        assertEquals("8:24:01 AM", timeExport.getTime());
        assertEquals("America/Edmonton", timeExport.getTimezone());

        timeExport = new TimeExport("2017-08-27T17:24:01Z");
        assertEquals("11:24:01 AM", timeExport.getTime());
    }

    @Test
    public void testConvertionForDaylightSavings() {
        TimeExport timeExport = new TimeExport("2017-01-27T14:24:01Z");
        assertEquals("7:24:01 AM", timeExport.getTime());
        assertEquals("America/Edmonton", timeExport.getTimezone());
    }

}