package com.planradar.weather.ui.home;

import android.view.View;

import com.planradar.weather.models.CityModel;


public interface CitiesItemClick {
    void cityItemClickListener(CityModel cityModel, View view);
}
