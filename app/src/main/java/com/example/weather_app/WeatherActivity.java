package com.example.weather_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;



public class WeatherActivity extends AppCompatActivity {
    private TextView cityWeather, temperatureWeather,weatherConditionWeather,humidityWeather,maxTemperatureWeather,minTemperatureWeather,pressureWeather,windWeather;
    private ImageView imageViewWeather;
    private Button search;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        cityWeather = findViewById(R.id.textViewCityWeather);
        temperatureWeather = findViewById(R.id.textViewTempWeather);
        weatherConditionWeather = findViewById(R.id.textViewWeatherConditionWeather);
        humidityWeather = findViewById(R.id.textViewHumidityWeather);
        maxTemperatureWeather = findViewById(R.id.textViewMaxTempWeather);
        minTemperatureWeather = findViewById(R.id.textViewMinTempWeather);
        pressureWeather = findViewById(R.id.textViewPressureWeather);
        windWeather = findViewById(R.id.textViewWindWeather);

        imageViewWeather = findViewById(R.id.imageViewWeather);

        search = findViewById(R.id.search);

        editText = findViewById(R.id.edittextcityname);


        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });




    }
}