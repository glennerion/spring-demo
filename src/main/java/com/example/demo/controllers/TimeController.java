package com.example.demo.controllers;


import java.io.IOException;

import javax.naming.ServiceUnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TimeExport;
import com.example.demo.model.Weather;
import com.example.demo.service.WeatherFetcher;

/**
 * A REST controller to get the current time from a weather service.
 */
@RestController
public class TimeController {
    /**
     * The weather service.
     */
    private WeatherFetcher weatherFetcher;

    /**
     * Constructor with the weatherFetcher service autowired.
     *
     * @param weatherFetcher pass in the Weather service.
     */
    @Autowired
    public TimeController(WeatherFetcher weatherFetcher) {
        this.weatherFetcher = weatherFetcher;
    }

    /**
     * Get the current time as an example of how to use another REST service.
     *
     * NOTE: If the back end rest service is not available, then a ServiceUnavailableException will be thrown and
     *       the user will get back a 503 Service Unavailable.
     *
     * @return The time as a json object.
     * @throws ServiceUnavailableException If we could not get the time
     */
    @RequestMapping(value = "/time/now", method = RequestMethod.GET, produces = "application/json")
    public TimeExport getTime() throws IOException, ServiceUnavailableException {
        Weather weatherJson = weatherFetcher.getWeather();
        return new TimeExport(weatherJson.getCreatedTime());
    }

}
