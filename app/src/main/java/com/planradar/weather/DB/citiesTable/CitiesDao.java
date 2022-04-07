package com.planradar.weather.DB.citiesTable;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.planradar.weather.models.CityModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;

@Dao
public interface CitiesDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable InsertCities(List<CityModel> cities);

    @Query("SELECT * FROM cities_table")
    Maybe<List<CityModel>> getCities();


    @Query("SELECT * FROM cities_table Where selected Like 1")
    Maybe<List<CityModel>> getSelectedCities();


    @Update
    void updateCity(CityModel cityModel);






}
