package com.planradar.weather.models;


import android.os.Parcel;
import android.os.Parcelable;



public class WeatherRecordModel implements Parcelable {

    int id;
    int cityId;
    String imageId;
    String description;
    String temperature;
    String date;

    public WeatherRecordModel() {
    }

    public WeatherRecordModel(int id, int cityId, String imageId, String description, String temperature, String date) {
        this.id = id;
        this.cityId = cityId;
        this.imageId = imageId;
        this.description = description;
        this.temperature = temperature;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.cityId);
        dest.writeString(this.imageId);
        dest.writeString(this.description);
        dest.writeString(this.temperature);
        dest.writeString(this.date);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.cityId = source.readInt();
        this.imageId = source.readString();
        this.description = source.readString();
        this.temperature = source.readString();
        this.date = source.readString();
    }

    protected WeatherRecordModel(Parcel in) {
        this.id = in.readInt();
        this.cityId = in.readInt();
        this.imageId = in.readString();
        this.description = in.readString();
        this.temperature = in.readString();
        this.date = in.readString();
    }

    public static final Creator<WeatherRecordModel> CREATOR = new Creator<WeatherRecordModel>() {
        @Override
        public WeatherRecordModel createFromParcel(Parcel source) {
            return new WeatherRecordModel(source);
        }

        @Override
        public WeatherRecordModel[] newArray(int size) {
            return new WeatherRecordModel[size];
        }
    };
}
