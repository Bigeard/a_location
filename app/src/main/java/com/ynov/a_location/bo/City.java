package com.ynov.a_location.bo;

import android.os.Parcel;
import android.os.Parcelable;


public class City implements Parcelable {
    String id, name;
    CityImage pic;

    public City(String id, String name, CityImage cityImage) {
        this.id = id;
        this.name = name;
        this.pic = cityImage;
    }


    protected City(Parcel in) {
        id = in.readString();
        name = in.readString();
        pic = in.readParcelable(CityImage.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeParcelable(pic, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityImage getImage() {
        return pic;
    }

    public void setImage(CityImage cityImage) {
        this.pic = cityImage;
    }
}
