package com.planradar.weather.models;

import android.os.Parcel;
import android.os.Parcelable;

public class WindModel implements Parcelable {

    float speed;



    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.speed);
    }

    public void readFromParcel(Parcel source) {
        this.speed = source.readFloat();
    }

    public WindModel() {
    }

    protected WindModel(Parcel in) {
        this.speed = in.readFloat();
    }

    public static final Parcelable.Creator<WindModel> CREATOR = new Parcelable.Creator<WindModel>() {
        @Override
        public WindModel createFromParcel(Parcel source) {
            return new WindModel(source);
        }

        @Override
        public WindModel[] newArray(int size) {
            return new WindModel[size];
        }
    };
}
