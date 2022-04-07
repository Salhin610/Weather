package com.planradar.weather.ui.history;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.planradar.weather.DB.CitiesDataBase;
import com.planradar.weather.models.CityModel;
import com.planradar.weather.models.WeatherRecordModel;

import java.util.List;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class HistoryViewModel extends ViewModel {
    MutableLiveData<List<WeatherRecordModel>> weatherRecordHistory = new MutableLiveData<>();

     public void getHistory(Context context, CityModel cityModel) {

        CitiesDataBase.getInstance(context)
                .CitiesDao()
                .getCity(cityModel.getCityId())
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<CityModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(CityModel city) {

                        weatherRecordHistory.setValue(city.getWeatherRecordModelList());

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