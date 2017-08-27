package com.example.demo.utils;

/**
 * A class to convert strings to numbers.
 */
public class NumberConverter {

    /**
     * Convert string to a number.
     *
     * Note: Just for fun, you can specify the numbers as hex, octal and binary in addition to standard numbers.
     *
     * @param number the String to convert
     * @return the nubmer as a double.
     */
    public static double convertToDouble(String number) {
        // Accept hex numbers
        if (number.toLowerCase().indexOf("0x") == 0) {
            return (double) Long.parseLong(number.substring(2), 16);
        }
        // Accept binary numbers
        if (number.toLowerCase().indexOf("0b") == 0) {
            return (double) Long.parseLong(number.substring(2), 2);
        }
        // Accept octal numbers
        if (number.startsWith("0")) {
            return (double) Long.parseLong(number, 8);
        }
        // In case people use the weird java 8 number syntax
        number = number.replace("_", "");
        return Double.valueOf(number);

    }
}
