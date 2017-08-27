package com.example.demo.controllers;

import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.utils.NumberConverter;

// TODO: javadoc
@RestController
public class MathController {


    @RequestMapping("/math/add")
    public double addDouble(@RequestParam double n1, @RequestParam double n2) {
        return n1 + n2;
    }

    @RequestMapping(value = "/math/add", method = { RequestMethod.POST })
    public double addPost(WebRequest request) {
        String n1 = request.getParameter("n1");
        String n2 = request.getParameter("n2");
        return addPost(n1, n2);
    }

    protected double addPost(String n1, String n2) {
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
