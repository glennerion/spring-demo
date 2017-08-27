package com.example.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.naming.ServiceUnavailableException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.model.Weather;
import com.google.gson.Gson;

/**
 * This service fetches the weather from the given URL.
 *
 * It assumes that the weather is in the yahoo format.
 *
 * NOTE: this uses Gson instead of RestTemplate because Gson is much more forgiving of changes in the JSON.
 */
@Service
public class WeatherFetcher {

    /**
     * The Json converter.
     */
    private static final Gson gson = new Gson();
    /**
     * Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(WeatherFetcher.class);

    /**
     * The URL to get the time from.
     */
    @Value("${weather.yahooUrl}")
    private String weatherYahooUrl;

    /**
     * Fetch the current weather forecast.
     *
     * @return The Weather POJO.
     * @throws ServiceUnavailableException The service is unavailable.
     */
    public Weather getWeather() throws ServiceUnavailableException {
        BufferedReader rd = null;
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(weatherYahooUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();

            return gson.fromJson(result.toString(), Weather.class);
        } catch (Exception ex) {
            if (rd != null) {
                try {
                    rd.close();
                } catch (IOException e) {
                    // Do nothing
                }
            }
            logger.error("Failed to get and convert the weather object", ex);
            throw new ServiceUnavailableException("Weather service is not available");
        }
    }
}
