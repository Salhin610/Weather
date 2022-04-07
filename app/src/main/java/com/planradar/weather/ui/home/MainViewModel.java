package com.planradar.weather.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.planradar.weather.models.CityModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    MutableLiveData<List<CityModel>> cities = new MutableLiveData<>();
    MutableLiveData<List<CityModel>> allCities = new MutableLiveData<>();
    public void getCities(){
        List<CityModel> citiesList = new ArrayList<>();
        for (int i = 0 ; i < 5 ; i++){
            CityModel cityModel = new CityModel(i, "Cairo", "EG",false);

            citiesList.add(cityModel);
        }

        cities.setValue(citiesList);
    }

    public void getAllCities() {
        List<CityModel> citiesList = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i++){
            CityModel cityModel = new CityModel(i, "Cairo", "EG",false);

            citiesList.add(cityModel);
        }

        allCities.setValue(citiesList);
    }
}