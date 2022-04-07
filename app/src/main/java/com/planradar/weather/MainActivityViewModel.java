package com.planradar.weather;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.planradar.weather.DB.CitiesDataBase;
import com.planradar.weather.models.CityModel;
import com.planradar.weather.utils.SharedPreferencesController;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel {

    MutableLiveData<List<CityModel>> citiesList = new MutableLiveData<>();



    public void saveCitiesToRoomDB(Context context, List<CityModel> cities) {

            CitiesDataBase.getInstance(context)
                    .CitiesDao()
                    .InsertCities(cities)
                    .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread()).
                    subscribe(new CompletableObserver() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onComplete() {
                    SharedPreferencesController.setAppOpenedBefore();
                }

                @Override
                public void onError(Throwable e) {

                    Log.e("Error",e.getMessage());
                }
            });

    }


    public void setCities() {
        Log.e("getCities", "go");
        List<CityModel> cityModelList = new ArrayList<>();
        cityModelList.add(new CityModel(0,"Cairo","EG",false, new ArrayList<>()));
        cityModelList.add(new CityModel(0,"London","UK",false, new ArrayList<>()));
        cityModelList.add(new CityModel(0,"Paris","FR",false, new ArrayList<>()));
        cityModelList.add(new CityModel(0,"Vienna","AUT",false, new ArrayList<>()));
        cityModelList.add(new CityModel(0,"Rome","IT",false, new ArrayList<>()));
        cityModelList.add(new CityModel(0,"Sydney","AST",false, new ArrayList<>()));
        citiesList.setValue(cityModelList);
    }
}
