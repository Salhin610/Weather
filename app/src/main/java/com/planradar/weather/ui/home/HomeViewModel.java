package com.planradar.weather.ui.home;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.planradar.weather.DB.citiesTable.CitiesDataBase;
import com.planradar.weather.models.CityModel;
import java.util.List;
import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {
    MutableLiveData<List<CityModel>> cities = new MutableLiveData<>();
    MutableLiveData<List<CityModel>> allCities = new MutableLiveData<>();

    public void getAllCities(Context context) {
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
                        allCities.setValue(cityModels);
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
                        cities.setValue(cityModels);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}