package com.planradar.weather;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.planradar.weather.DB.citiesTable.CitiesDataBase;
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

                }
            });

    }


    public void setCities() {
        List<CityModel> cityModelList = new ArrayList<>();
        cityModelList.add(new CityModel(0,"Cairo","EG",true));
        cityModelList.add(new CityModel(0,"London","UK",true));
        cityModelList.add(new CityModel(0,"Paris","FR",true));
        cityModelList.add(new CityModel(0,"Vienna","AUT",false));
        cityModelList.add(new CityModel(0,"Rome","IT",false));
        cityModelList.add(new CityModel(0,"Sydney","AST",false));
        citiesList.setValue(cityModelList);
    }
}
