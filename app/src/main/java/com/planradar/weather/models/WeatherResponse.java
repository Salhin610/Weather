package com.planradar.weather.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


public class WeatherResponse implements Parcelable {

//    @PrimaryKey
    String name;
    TempModel main;
    WindModel wind;
    List<WeatherModel> weather;

    public WeatherResponse() {
    }


    public WeatherResponse(TempModel main, WindModel wind, List<WeatherModel> weather) {
        this.main = main;
        this.wind = wind;
        this.weather = weather;
    }

    public TempModel getMain() {
        return main;
    }

    public void setMain(TempModel main) {
        this.main = main;
    }

    public WindModel getWind() {
        return wind;
    }

    public void setWind(WindModel wind) {
        this.wind = wind;
    }

    public List<WeatherModel> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherModel> weather) {
        this.weather = weather;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.main, flags);
        dest.writeParcelable(this.wind, flags);
        dest.writeList(this.weather);
    }

    public void readFromParcel(Parcel source) {
        this.main = source.readParcelable(TempModel.class.getClassLoader());
        this.wind = source.readParcelable(WindModel.class.getClassLoader());
        this.weather = new ArrayList<WeatherModel>();
        source.readList(this.weather, WeatherModel.class.getClassLoader());
    }

    protected WeatherResponse(Parcel in) {
        this.main = in.readParcelable(TempModel.class.getClassLoader());
        this.wind = in.readParcelable(WindModel.class.getClassLoader());
        this.weather = new ArrayList<WeatherModel>();
        in.readList(this.weather, WeatherModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<WeatherResponse> CREATOR = new Parcelable.Creator<WeatherResponse>() {
        @Override
        public WeatherResponse createFromParcel(Parcel source) {
            return new WeatherResponse(source);
        }

        @Override
        public WeatherResponse[] newArray(int size) {
            return new WeatherResponse[size];
        }
    };
}
