package me.adjagueye.gcu.mpd.weatherforecast.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Calendar;
/**
 * @author Adja Gueye
 * Student ID: S2110852S
 */
public class JobBroadcastReceiver extends BroadcastReceiver {
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    @Override
    public void onReceive(Context context, Intent ignore) {
        Log.d("Broadcast", "Scheduled update job started");
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, WeatherUpdateService.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        int time = sharedPref.getInt("weather_update", 8);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, time);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);

    }
}
