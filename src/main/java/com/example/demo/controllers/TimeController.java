package com.example.demo.controllers;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TimeExport;
import com.example.demo.model.Weather;
import com.example.demo.service.WeatherFetcher;

@RestController
public class TimeController {
    private WeatherFetcher weatherFetcher;

    @Autowired
    public TimeController(WeatherFetcher airportFetcher) {
        this.weatherFetcher = airportFetcher;
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET, produces = "application/json")
    public TimeExport getTime() throws IOException {
        // TODO: if exception, then temporarily unavalible
        Weather weatherJson = weatherFetcher.getWeather();
        return new TimeExport(weatherJson.getCreatedTime());
    }

}
