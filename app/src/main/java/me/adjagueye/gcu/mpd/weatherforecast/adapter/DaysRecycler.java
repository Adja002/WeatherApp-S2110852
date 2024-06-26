package me.adjagueye.gcu.mpd.weatherforecast.adapter;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import me.adjagueye.gcu.mpd.weatherforecast.R;
import me.adjagueye.gcu.mpd.weatherforecast.activities.MoreDetail;
import me.adjagueye.gcu.mpd.weatherforecast.model.Forecast;
import me.adjagueye.gcu.mpd.weatherforecast.model.WeatherDetail;

/**
 * @author Adja Gueye
 * Student ID: S2110852
 */
public class DaysRecycler extends RecyclerView.Adapter<DaysRecycler.ViewHolder> {

    Forecast forecast;
    Context context;
    String city;

    public DaysRecycler(Forecast forecast,String city) {
        this.forecast = forecast;
        this.city = city;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final WeatherDetail weatherDetail = forecast.getItems().get(position);
        holder.title.setText(weatherDetail.getTitle());
        holder.image.setImageResource(getIdentifier(city.toLowerCase().replaceAll(" ","_"),"drawable"));

//      holder.weatherIcon.setText(getIdentifier(weatherDetail.getTitle().toLowerCase().replaceAll(" ","_"),"string"));
        String temp = weatherDetail.getMinimumTemperature().split(":")[1];
        boolean tempSetting = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("temperature", false);
        temp = tempSetting ? temp.split("\\(")[0] : temp;
        holder.minimumTemperature.setText(temp);
        holder.windSpeed.setText(weatherDetail.getWindSpeed());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MoreDetail.class);
                intent.putExtra("data", weatherDetail);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return forecast.getItems().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView weatherIcon;
        TextView minimumTemperature;
        TextView windSpeed;
        TextView windDirection;
        CardView cv;
        Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.card_image);
            title = (TextView) itemView.findViewById(R.id.card_title);
            weatherIcon = (TextView) itemView.findViewById(R.id.weather);
            minimumTemperature = (TextView) itemView.findViewById(R.id.minimum_temperature);
            windDirection = (TextView) itemView.findViewById(R.id.wind_direction);
            windSpeed = (TextView) itemView.findViewById(R.id.wind_speed);
            cv = (CardView) itemView.findViewById(R.id.cv);
            button = (Button) itemView.findViewById(R.id.more_details);
        }
    }

    private int getIdentifier(String name, String defType) {
        String packageName = context.getPackageName();
        if(defType.equals("string")){
            name = "wi_"+name;
        }
        int res;
        try {
            res = context.getResources().getIdentifier(name, defType, packageName);
        }catch (Exception e){
            res = context.getResources().getIdentifier("wi_cloud", defType, packageName);
        }

        return res;
    }
}
