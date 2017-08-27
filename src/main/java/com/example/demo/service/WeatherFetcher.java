package com.example.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.example.demo.model.Weather;
import com.google.gson.Gson;

@Service
public class WeatherFetcher {

    private static Gson gson = new Gson();
    // TODO: move out to configuration file so that can be configured on a per environment basis.
    private static final String yahooUrl = "https://query.yahooapis.com/v1/public/yql?q=select%20item.condition%20from%20weather.forecast%20where%20woeid%20%3D%202487889&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

    public Weather getWeather() throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(yahooUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();

        return gson.fromJson(result.toString(), Weather.class);
    }
}
