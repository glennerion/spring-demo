package com.example.demo.controllers;

import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.utils.NumberConverter;

/**
 * Simple REST controller to add two numbers together.
 */
@RestController
public class MathController {

    /**
     * Add two numbers together and return the result.
     *
     * @param n1 first number to add
     * @param n2 second number to add
     * @return The sum of the two numbers
     */
    @RequestMapping(value = "/math/add", method = { RequestMethod.GET })
    public double addDouble(@RequestParam double n1, @RequestParam double n2) {
        return n1 + n2;
    }

    /**
     * Post handler for form data n1 and n2 to add two numbers together. Note that if either number is
     * missing or not a number, then the system will return a 400 Bad Request with an appropriate message.
     *
     * Note: Just for fun, you can specify the numbers as hex, octal and binary in addition to standard numbers.
     *
     * @param n1 first number to add
     * @param n2 second number to add
     * @return The sum of two numbers.
     */
    @RequestMapping(value = "/math/add", method = { RequestMethod.POST })
    public double addPost(@RequestParam String n1, @RequestParam String n2) {
        return addPostedValues(n1, n2);
    }

    /**
     * Add two numbers passed in.
     *
     * @param n1 First number
     * @param n2 Second Number
     * @return the sum of the two numbers.
     */
    protected double addPostedValues(String n1, String n2) {
        if (StringUtils.isEmpty(n1) || StringUtils.isEmpty(n2)) {
            throw new IllegalArgumentException("You must pass two form parameters with numerical values: n1 and n2");
        }

        try {
            double d1 = NumberConverter.convertToDouble(n1);
            double d2 = NumberConverter.convertToDouble(n2);
            return d1 + d2;
        } catch(Exception ex) {
            throw new IllegalArgumentException("n1 and n2 must be numbers");
        }
    }
}
