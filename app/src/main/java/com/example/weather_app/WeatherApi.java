package com.example.weather_app;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {


    @GET ("weather?appid=25b2e3f5643449e85c581fa7939b97a1&units=metric")
    Call<OpenWeatherMap> getWeatherWithLocation(@Query("lat") double lat,@Query("lon") double lon);


    @GET ("weather?appid=25b2e3f5643449e85c581fa7939b97a1&units=metric")
    Call<OpenWeatherMap> getWeatherWithCity(@Query("q") String name);
}
