package me.adjagueye.gcu.mpd.weatherforecast.service;

import java.util.ArrayList;
import java.util.List;

import me.adjagueye.gcu.mpd.weatherforecast.model.Forecast;

/**
 * @author Adja Gueye
 * Student ID: S2110852
 */
public class ForecastRepository {

    private static ForecastRepository FORECAST_REPOSITORY = null;
    List<Forecast> forecastList;
    private ForecastRepository() {
        forecastList = new ArrayList<>();
    }
    public static ForecastRepository getInstance() {
        if (FORECAST_REPOSITORY == null) {
            synchronized(ForecastRepository.class) {
                FORECAST_REPOSITORY = new ForecastRepository();
            }
        }
        return FORECAST_REPOSITORY;
    }
}
