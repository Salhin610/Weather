package com.planradar.weather.ui.weather;


import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.planradar.weather.DB.CitiesDataBase;
import com.planradar.weather.models.CityModel;
import com.planradar.weather.models.WeatherRecordModel;
import com.planradar.weather.models.WeatherResponse;
import com.planradar.weather.network.APIClient;
import com.planradar.weather.network.APIInterface;
import com.planradar.weather.utils.Constants;
import com.planradar.weather.utils.KelvinToCelsius;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WeatherViewModel extends ViewModel implements Constants {

    MutableLiveData<String> description = new MutableLiveData<>();
    MutableLiveData<String> temperature = new MutableLiveData<>();
    MutableLiveData<String> humidity = new MutableLiveData<>();
    MutableLiveData<String> windSpeed = new MutableLiveData<>();
    MutableLiveData<String> iconLink = new MutableLiveData<>();
    MutableLiveData<String> onFailure = new MutableLiveData<>();
    MutableLiveData<String> callDate = new MutableLiveData<>();
    MutableLiveData<Boolean> callSucceed = new MutableLiveData<>();


    public void getWeather(Context context, CityModel cityModel){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<WeatherResponse> call = apiInterface.getWeatherForSelectedCity(cityModel.getCityName(),appId);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                WeatherResponse resource=response.body();
                if (response.isSuccessful()){
                    WeatherRecordModel weatherRecordModel = new WeatherRecordModel();
                    callSucceed.setValue(true);
                    temperature.setValue(KelvinToCelsius.kelvinToCelsius(resource.getMain().getTemp()));
                    humidity.setValue(resource.getMain().getHumidity()+"%");
                    windSpeed.setValue(resource.getWind().getSpeed()+"Km/h");

                    if (resource.getWeather().size()!=0){
                        description.setValue(resource.getWeather().get(0).getDescription());
                        iconLink.setValue(resource.getWeather().get(0).getIcon());
                    }else{
                        description.setValue("N/A");
                        iconLink.setValue("N/A");
                    }

                    weatherRecordModel.setCityId(cityModel.getCityId());
                    weatherRecordModel.setId(0);
                    weatherRecordModel.setTemperature(temperature.getValue());

                    String date="";
                    SimpleDateFormat spf=new SimpleDateFormat(dateFormat);
                    Date currentTime = Calendar.getInstance().getTime();

                    spf= new SimpleDateFormat("dd.MM.yyyy. hh:mm a");
                    date = spf.format(currentTime);

                    Log.e("Date", date);
                    callDate.setValue(date);
                    weatherRecordModel.setDate(date);
                    weatherRecordModel.setDescription(description.getValue());
                    weatherRecordModel.setImageId(iconLink.getValue());


                    saveRecordToDB(context,weatherRecordModel,cityModel);

                }else
                    callSucceed.setValue(false);


            }
            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                onFailure.setValue(t.getMessage());
            }
        });

    }

    private void saveRecordToDB(Context context, WeatherRecordModel weatherRecordModel, CityModel cityModel) {

        cityModel.getWeatherRecordModelList().add(weatherRecordModel);
        new Thread(() -> CitiesDataBase.getInstance(context).CitiesDao().
                updateCity(cityModel)).start();


    }
}