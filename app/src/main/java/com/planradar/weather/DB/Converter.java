package com.planradar.weather.DB;


import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.planradar.weather.models.WeatherRecordModel;

import java.lang.reflect.Type;
import java.util.List;

@TypeConverters(Converter.class)
 public class Converter {

    @TypeConverter
    public String fromCountryLangList(List<WeatherRecordModel> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<WeatherRecordModel>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<WeatherRecordModel> toCountryLangList(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<WeatherRecordModel>>() {}.getType();
        List<WeatherRecordModel> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }


}
