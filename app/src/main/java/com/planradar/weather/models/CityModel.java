package com.planradar.weather.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cities_table")
public class CityModel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    int cityId;
    String cityName;
    String country;
    boolean selected = false;

    public CityModel() {
    }

    public CityModel(int cityId, String cityName, String country, boolean selected ) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.country = country;
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.cityId);
        dest.writeString(this.cityName);
        dest.writeString(this.country);
        dest.writeByte(this.selected ? (byte) 1 : (byte) 0);
    }

    public void readFromParcel(Parcel source) {
        this.cityId = source.readInt();
        this.cityName = source.readString();
        this.country = source.readString();
        this.selected = source.readByte() != 0;
    }

    protected CityModel(Parcel in) {
        this.cityId = in.readInt();
        this.cityName = in.readString();
        this.country = in.readString();
        this.selected = in.readByte() != 0;
    }

    public static final Creator<CityModel> CREATOR = new Creator<CityModel>() {
        @Override
        public CityModel createFromParcel(Parcel source) {
            return new CityModel(source);
        }

        @Override
        public CityModel[] newArray(int size) {
            return new CityModel[size];
        }
    };
}
