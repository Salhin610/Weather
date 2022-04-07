package com.planradar.weather.ui.home;


import com.planradar.weather.models.CityModel;

public interface CityAddedRemovedListener {
    void addCity(CityModel cityModel);
    void removeCity(CityModel cityModel);
}
