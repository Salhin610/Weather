package com.planradar.weather.ui.home;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.planradar.weather.DB.CitiesDataBase;
import com.planradar.weather.models.CityModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {
    MutableLiveData<List<CityModel>> cities = new MutableLiveData<>();
    MutableLiveData<List<CityModel>> allCities = new MutableLiveData<>();


    private void getSearchCities(List<CityModel> cities, String searchKey) {
        List<CityModel> tempCities = new ArrayList<>();
        for (int i = 0 ; i < cities.size() ; i++){

            if (cities.get(i).getCityName().toLowerCase().contains(searchKey.toLowerCase()))
                tempCities.add(cities.get(i));
            else if (cities.get(i).getCountry().toLowerCase().contains(searchKey.toLowerCase()))
                tempCities.add(cities.get(i));

        }

        allCities.setValue(tempCities);

    }

    public void getAllCities(Context context, String searchKey){
        CitiesDataBase.getInstance(context)
                .CitiesDao()
                .getCities()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<CityModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<CityModel> cityModels) {
                        if (searchKey == null)
                            allCities.setValue(cityModels);
                        else
                            getSearchCities(cityModels, searchKey);
                    }

                    @Override
                    public void onError(Throwable e) { }

                    @Override
                    public void onComplete() { }
                });
    }

    public void retrieveSelectedCitiesFromRoomDB(Context context) {
        CitiesDataBase.getInstance(context)
                .CitiesDao()
                .getSelectedCities()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<CityModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<CityModel> cityModels) {
                        Log.e("Success",cityModels.size()+" ff");

                        cities.setValue(cityModels);

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("Error",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}