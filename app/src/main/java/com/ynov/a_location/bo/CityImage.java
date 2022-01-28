package com.ynov.a_location.bo;

import android.os.Parcel;
import android.os.Parcelable;

public class CityImage implements Parcelable {
    String url;

    public CityImage(String url) {
        this.url = url;
    }

    protected CityImage(Parcel in) {
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CityImage> CREATOR = new Creator<CityImage>() {
        @Override
        public CityImage createFromParcel(Parcel in) {
            return new CityImage(in);
        }

        @Override
        public CityImage[] newArray(int size) {
            return new CityImage[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

