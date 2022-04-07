package com.planradar.weather.network;


import com.planradar.weather.models.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("data/2.5/weather")
    Call<WeatherResponse> getWeatherForSelectedCity(@Query("q") String cityName, @Query("appid") String AppId);


}
