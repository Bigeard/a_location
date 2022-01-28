package com.ynov.a_location.bo;

import android.os.Parcel;
import android.os.Parcelable;

public class HousingImage implements Parcelable {
    String url;

    public HousingImage(String url) {
        this.url = url;
    }

    protected HousingImage(Parcel in) {
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

    public static final Creator<HousingImage> CREATOR = new Creator<HousingImage>() {
        @Override
        public HousingImage createFromParcel(Parcel in) {
            return new HousingImage(in);
        }

        @Override
        public HousingImage[] newArray(int size) {
            return new HousingImage[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

