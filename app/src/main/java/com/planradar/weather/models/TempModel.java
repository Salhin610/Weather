package com.planradar.weather.models;

import android.os.Parcel;
import android.os.Parcelable;

public class TempModel implements Parcelable {
    float temp;
    int humidity;


    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.temp);
        dest.writeInt(this.humidity);
    }

    public void readFromParcel(Parcel source) {
        this.temp = source.readFloat();
        this.humidity = source.readInt();
    }

    public TempModel() {
    }

    protected TempModel(Parcel in) {
        this.temp = in.readFloat();
        this.humidity = in.readInt();
    }

    public static final Parcelable.Creator<TempModel> CREATOR = new Parcelable.Creator<TempModel>() {
        @Override
        public TempModel createFromParcel(Parcel source) {
            return new TempModel(source);
        }

        @Override
        public TempModel[] newArray(int size) {
            return new TempModel[size];
        }
    };
}
