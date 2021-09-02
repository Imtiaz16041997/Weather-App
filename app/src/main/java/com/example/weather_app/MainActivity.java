package com.example.weather_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView city, temperature,weatherCondition,humidity,maxTemperature,minTemperature,pressure,wind;
    private ImageView imageView;
    private FloatingActionButton fab;

    LocationManager locationManager;
    LocationListener locationListener;

    double lat,lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = findViewById(R.id.textViewCity);
        temperature = findViewById(R.id.textViewTemp);
        weatherCondition = findViewById(R.id.textViewWeatherCondition);
        humidity = findViewById(R.id.textViewHumidity);
        maxTemperature = findViewById(R.id.textViewMaxTemp);
        minTemperature = findViewById(R.id.textViewMinTemp);
        pressure = findViewById(R.id.textViewPressure);
        imageView = findViewById(R.id.imageView);
        wind = findViewById(R.id.textViewWind);
        fab = findViewById(R.id.fab);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                //that's how get the user location
                lat = location.getLatitude();
                lon = location.getLongitude();

                Log.e("lat : ",String.valueOf(lat));
                Log.e("lon : ",String.valueOf(lon));


                getWeatherData(lat,lon);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }
        };
        
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        
        {

            ActivityCompat.requestPermissions(this,new String []{Manifest.permission.ACCESS_FINE_LOCATION},1);
            
            
        }
        else

        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,500,50,locationListener);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if(requestCode == 1 && permissions.length > 0 &&
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)

        {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,500,50,locationListener);

        }

    }

    public void getWeatherData(Double lat, Double lon){

        WeatherApi weatherApi = RetrofitWeather.getClient().create(WeatherApi.class);
        Call<OpenWeatherMap>  call =  weatherApi.getWeatherWithLocation(lat,lon);


        call.enqueue(new Callback<OpenWeatherMap>() {
            @Override
            public void onResponse(Call<OpenWeatherMap> call, Response<OpenWeatherMap> response) {

                city.setText(response.body().getName()+ " , "+response.body().getSys().getCountry());
                temperature.setText(response.body().getMain().getTemp()+" °C");
                weatherCondition.setText(response.body().getWeather().get(0).getDescription());
                humidity.setText(" : "+response.body().getMain().getHumidity()+"%");
                maxTemperature.setText(" : "+response.body().getMain().getTempMax()+" °C");
                minTemperature.setText(" : "+response.body().getMain().getTempMin()+" °C");
                pressure.setText(" : "+response.body().getMain().getPressure()+" °C");
                wind.setText(" : "+response.body().getWind().getSpeed());


                String iconCode = response.body().getWeather().get(0).getIcon();
                Picasso.get().load(" http://openweathermap.org/img/wn/"+iconCode+"@2x.png")
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(imageView);


            }

            @Override
            public void onFailure(Call<OpenWeatherMap> call, Throwable t) {

            }
        });

    }


}