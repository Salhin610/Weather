package com.planradar.weather.ui.weather;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.planradar.weather.models.WeatherResponse;
import com.planradar.weather.network.APIClient;
import com.planradar.weather.network.APIInterface;
import com.planradar.weather.utils.Constants;
import com.planradar.weather.utils.KelvinToCelsius;
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
    MutableLiveData<Boolean> callSucceed = new MutableLiveData<>();


    public void getWeather(String cityName){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<WeatherResponse> call = apiInterface.getWeatherForSelectedCity(cityName,appId);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                WeatherResponse resource=response.body();
                if (response.isSuccessful()){
                    callSucceed.setValue(true);
                    //Todo Save record To room with country name as a key
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

                }else
                    callSucceed.setValue(false);


            }
            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                onFailure.setValue(t.getMessage());
            }
        });

    }
}