package com.planradar.weather.DB.citiesTable;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.planradar.weather.models.CityModel;


@Database(entities = CityModel.class,version = 1)
abstract public class CitiesDataBase extends RoomDatabase {

    private static CitiesDataBase instance;
    public abstract CitiesDao CitiesDao();


    // - singleton
    // - synchronized : protect from concurrent execution by multiple threads
    public static synchronized CitiesDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), CitiesDataBase.class,"cities_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
