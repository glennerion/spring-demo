package com.example.demo.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the NumberConverter.
 */
public class NumberConverterTest {
    @Test
    public void testConvertInt() throws Exception {
        double number = NumberConverter.convertToDouble("10");
        assertEquals(10.0, number, 0);
    }

    @Test
    public void testConvertDouble() throws Exception {
        double number = NumberConverter.convertToDouble("10.7");
        assertEquals(10.7, number, 0);
    }

    @Test
    public void testConvertWeirdJava8() throws Exception {
        double number = NumberConverter.convertToDouble("1_0.7_1");
        assertEquals(10.71, number, 0);
    }

    @Test
    public void testConvertHex() throws Exception {
        double number = NumberConverter.convertToDouble("0X10");
        assertEquals(16.0, number, 0);
    }

    @Test
    public void testConvertOctal() throws Exception {
        double number = NumberConverter.convertToDouble("010");
        assertEquals(8.0, number, 0);
    }

    @Test
    public void testConvertBinary() throws Exception {
        double number = NumberConverter.convertToDouble("0b10");
        assertEquals(2.0, number, 0);
    }
}