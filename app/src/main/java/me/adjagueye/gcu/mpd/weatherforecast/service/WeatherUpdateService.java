package me.adjagueye.gcu.mpd.weatherforecast.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import me.adjagueye.gcu.mpd.weatherforecast.model.Forecast;
import me.adjagueye.gcu.mpd.weatherforecast.ui.main.PageViewModel;

/**
 * @author Adja Gueye
 * Student ID: S2110852
 */
public class WeatherUpdateService extends IntentService {
    private static final String TAG = WeatherUpdateService.class.getSimpleName();

    public WeatherUpdateService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG,"Job Started");
                WeatherService weatherService = new WeatherService(new PageViewModel());
                for (Forecast forecast: ForecastRepository.getInstance().forecastList){
                    weatherService.execute(forecast.getLocationId());
                }

    }
}
