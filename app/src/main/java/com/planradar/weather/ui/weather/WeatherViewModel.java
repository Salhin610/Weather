package com.planradar.weather.ui.weather;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.planradar.weather.utils.Constants;


public class WeatherViewModel extends ViewModel implements Constants {

    MutableLiveData<String> description = new MutableLiveData<>();
    MutableLiveData<String> temperature = new MutableLiveData<>();
    MutableLiveData<Integer> humidity = new MutableLiveData<>();
    MutableLiveData<Float> windSpeed = new MutableLiveData<>();
    MutableLiveData<String> iconLink = new MutableLiveData<>();

    public void getWeather(){


    }
}