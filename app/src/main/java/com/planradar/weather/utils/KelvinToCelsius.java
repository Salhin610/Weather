package com.planradar.weather.utils;

public class KelvinToCelsius {

    public static String kelvinToCelsius (float temperature){
        return Math.round(temperature-273) +" °C";
    }
}
